import pika
import json
import random
import time
import os
from datetime import datetime

SENSORS = [
    "temperatura_001",
    "humidade_001",
    "luz_001",
    "movimento_001",
    "energia_001"
]

host = os.getenv("RABBITMQ_HOST", "rabbitmq")

# Esperar RabbitMQ estar pronto
while True:
    try:
        connection = pika.BlockingConnection(
            pika.ConnectionParameters(host=host, port=5672)
        )
        break
    except Exception:
        print("A aguardar que o RabbitMQ fique disponível...")
        time.sleep(5)

channel = connection.channel()

# Garantir que a queue existe
channel.queue_declare(queue='telemetry_queue', durable=True)


def gerar_valor(sensor_id):
    if "temperatura" in sensor_id:
        return round(random.uniform(15, 30), 2)

    elif "humidade" in sensor_id:
        return round(random.uniform(30, 80), 2)

    elif "luz" in sensor_id or "movimento" in sensor_id:
        return float(random.randint(0, 1))

    elif "energia" in sensor_id:
        return round(random.uniform(0, 5), 2)

    return 0.0


while True:
    sensor_id = random.choice(SENSORS)

    mensagem = {
        "sensor": {
            "id": sensor_id
        },
        "valor": gerar_valor(sensor_id),
        "timestamp": datetime.utcnow().isoformat(timespec='seconds')
    }

    channel.basic_publish(
        exchange='',
        routing_key='telemetry_queue',
        body=json.dumps(mensagem).encode("utf-8"),
        properties=pika.BasicProperties(
            delivery_mode=2  # persistente
        )
    )

    print("Enviado:", mensagem)

    time.sleep(2)
