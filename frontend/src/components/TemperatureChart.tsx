import React, { useEffect, useState } from 'react';
import { LineChart, Line, XAxis, YAxis, CartesianGrid, Tooltip, ResponsiveContainer } from 'recharts';
import axios from 'axios';

interface TemperatureReading {
  timestamp: string; 
  valor: number;     
}

interface Props {
  sensorId: string;
}

const TemperatureChart: React.FC<Props> = ({ sensorId }) => {
  const [data, setData] = useState<TemperatureReading[]>([]);
  const [loading, setLoading] = useState<boolean>(true);

  useEffect(() => {
    const fetchHistory = async () => {
      setLoading(true);
      try {
        // CORREÇÃO: Usar o URL que confirmaste no Postman (sem o /dados)
        const url = `http://localhost:8080/api/sensores/${sensorId}`;
        const response = await axios.get(url);
        
        console.log("Resposta da API recebida:", response.data);

        // No teu JSON, os dados estão dentro da chave 'leituras'
        // Como o Postman devolveu um array [ {...} ], acedemos ao primeiro elemento
        const sensorData = Array.isArray(response.data) ? response.data[0] : response.data;
        
        if (sensorData && sensorData.leituras) {
          setData(sensorData.leituras);
        }
      } catch (err) {
        console.error("Erro ao carregar dados do sensor:", err);
      } finally {
        setLoading(false);
      }
    };

    if (sensorId) fetchHistory();
  }, [sensorId]);

  if (loading) return <p>A ligar à pipeline de dados...</p>;
  
  if (!data || data.length === 0) {
    return <p style={{ color: 'orange' }}>Ligado ao sensor {sensorId}, mas a lista de 'leituras' está vazia na BD.</p>;
  }

  return (
    <div style={{ width: '100%', height: 350, marginTop: '20px' }}>
      <ResponsiveContainer width="100%" height="100%">
        <LineChart data={data}>
          <CartesianGrid strokeDasharray="3 3" vertical={false} />
          <XAxis 
            dataKey="timestamp" 
            tickFormatter={(t) => t && t.includes('T') ? t.split('T')[1].substring(0, 5) : t}
            tick={{ fontSize: 10 }}
          />
          <YAxis unit="°C" domain={['auto', 'auto']} />
          <Tooltip labelFormatter={(t) => t.replace('T', ' ')} />
          <Line 
            type="monotone" 
            dataKey="valor" // Bate com o campo do teu JSON
            stroke="#ff7300" 
            strokeWidth={3} 
            dot={{ r: 2 }}
            animationDuration={800}
          />
        </LineChart>
      </ResponsiveContainer>
    </div>
  );
};

export default TemperatureChart;