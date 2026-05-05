import React, { useEffect, useState } from 'react';
import axios from 'axios';
import TemperatureChart from './components/TemperatureChart';

// Interface ajustada para o teu JSON real
interface Sensor {
  id: string;      // No teu JSON é "id"
  tipo: string;
  divisao: string; // No teu JSON é "divisao", não "localizacao"[cite: 4]
  unidade: string;
}

const App: React.FC = () => {
  const [sensores, setSensores] = useState<Sensor[]>([]);
  const [erro, setErro] = useState<string | null>(null);
  const [sensorSelecionado, setSensorSelecionado] = useState<string | null>(null);

  const fetchSensores = async () => {
    try {
      const response = await axios.get<Sensor[]>('http://localhost:8080/api/sensores');
      setSensores(response.data);
    } catch (err) {
      setErro("Erro de ligação: O Docker e o Backend estão ativos?");
      console.error(err);
    }
  };

  useEffect(() => {
    fetchSensores();
  }, []);

  return (
    <div style={{ padding: '20px', fontFamily: 'sans-serif', color: '#333' }}>
      <h1>SmartHome Dashboard - Diogo</h1>
      
      {erro && <p style={{ color: 'red', fontWeight: 'bold' }}>{erro}</p>}

      <h2 style={{ borderBottom: '2px solid #eee', paddingBottom: '10px' }}>Os Teus Sensores</h2>
      
      <div style={{ display: 'grid', gridTemplateColumns: 'repeat(auto-fit, minmax(250px, 1fr))', gap: '15px', marginBottom: '40px' }}>
        {sensores.length === 0 && !erro ? (
          <p>A carregar sensores...</p>
        ) : (
          sensores.map((sensor) => (
            <div 
              key={sensor.id} 
              style={{ 
                border: sensorSelecionado === sensor.id ? '2px solid #ff7300' : '1px solid #ddd', 
                padding: '15px', 
                borderRadius: '8px',
                cursor: 'pointer',
                backgroundColor: sensorSelecionado === sensor.id ? '#fff3e0' : '#fff'
              }}
              onClick={() => setSensorSelecionado(sensor.id)}
            >
              {/* Usa 'divisao' para mostrar "sala", "cozinha", etc.[cite: 4] */}
              <h3 style={{ marginTop: 0, textTransform: 'capitalize' }}>{sensor.divisao}</h3>
              <p><strong>Tipo:</strong> {sensor.tipo}</p>
              <p><strong>ID:</strong> {sensor.id}</p>
              <button 
                onClick={(e) => {
                  e.stopPropagation(); 
                  setSensorSelecionado(sensor.id);
                }}
                style={{
                  padding: '8px 12px',
                  backgroundColor: '#ff7300',
                  color: 'white',
                  border: 'none',
                  borderRadius: '4px',
                  cursor: 'pointer',
                  fontWeight: 'bold'
                }}
              >
                Ver Histórico
              </button>
            </div>
          ))
        )}
      </div>

      <h2 style={{ borderBottom: '2px solid #eee', paddingBottom: '10px' }}>
        Análise em Tempo Real {sensorSelecionado ? `- Sensor ${sensorSelecionado}` : ''}
      </h2>
      
      <div style={{ 
        maxWidth: '900px', 
        backgroundColor: '#fff', 
        borderRadius: '8px', 
        padding: '20px', 
        boxShadow: '0 4px 12px rgba(0,0,0,0.1)',
        minHeight: '350px'
      }}>
        {sensorSelecionado ? (
          /* FIX: Passar a variável entre chavetas, sem aspas */
          <TemperatureChart sensorId={sensorSelecionado} />
        ) : (
          <div style={{ textAlign: 'center', paddingTop: '100px', color: '#999' }}>
            <p>Selecione um sensor acima para carregar o histórico de telemetria.</p>
          </div>
        )}
      </div>
    </div>
  );
};

export default App;