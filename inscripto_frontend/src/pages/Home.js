import React, { useEffect, useState } from 'react';
import api from '../services/api';

function Home() {
  const [equipes, setEquipes] = useState([]);

  useEffect(() => {
    api.get('/equipe') // endpoint do seu controller
      .then((response) => {
        setEquipes(response.data);
      })
      .catch((error) => {
        console.error("Erro ao buscar equipes:", error);
      });
  }, []);

  return (
    <div>
      <h1>Equipes</h1>
      <ul>
        {equipes.map((equipe) => (
          <li key={equipe.id}>{equipe.nome}</li>
        ))}
      </ul>
    </div>
  );
}

export default Home;
