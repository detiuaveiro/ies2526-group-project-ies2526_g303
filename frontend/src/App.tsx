import { useEffect, useState } from 'react'
import axios from 'axios'

// 1. Define a interface para os teus dados (tem de bater com o teu Model Java)
interface Sensor {
  sensorId: string;
  tipo: string;
  localizacao: string;
  unidade: string;
}

interface SensorData {
  id: number;
  valor: number;
  timestamp: string;
}

function App() {
  const [sensores, setSensores] = useState<Sensor[]>([]);
  const [erro, setErro] = useState<string | null>(null);

  // 2. Função para ir buscar os dados à tua API
  const fetchSensores = async () => {
    try {
      // O URL tem de ser o que definiste no @RequestMapping do teu Controller
      const response = await axios.get<Sensor[]>('http://localhost:8080/api/sensores');
      setSensores(response.data);
    } catch (err) {
      setErro("Não consegui ligar ao backend. O Docker e o Spring Boot estão ligados?");
      console.error(err);
    }
  };

  // 3. Executa a função mal o site abre
  useEffect(() => {
    fetchSensores();
  }, []);

  return (
    <div style={{ padding: '20px', fontFamily: 'sans-serif' }}>
      <h1>SmartHome Dashboard - Diogo</h1>
      
      {erro && <p style={{ color: 'red' }}>{erro}</p>}

      <div style={{ display: 'grid', gridTemplateColumns: 'repeat(auto-fit, minmax(200px, 1fr))', gap: '15px' }}>
        {sensores.length === 0 && !erro ? (
          <p>Nenhum sensor encontrado na base de dados...</p>
        ) : (
          sensores.map(sensor => (
            <div key={sensor.sensorId} style={{ border: '1px solid #ddd', padding: '15px', borderRadius: '8px' }}>
              <h3>{sensor.localizacao}</h3>
              <p><strong>Tipo:</strong> {sensor.tipo}</p>
              <p><strong>ID:</strong> {sensor.sensorId}</p>
              <button onClick={() => alert(`A buscar detalhes para ${sensor.sensorId}...`)}>
                Ver Histórico
              </button>
            </div>
          ))
        )}
      </div>
    </div>
  )
}

export default App