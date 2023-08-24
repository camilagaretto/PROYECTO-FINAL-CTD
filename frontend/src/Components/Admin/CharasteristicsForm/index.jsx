import React, {useEffect, useState } from 'react';
import { useParams } from 'react-router-dom'
import './styles.scss'

function CharasteristicsForm() {
    const params = useParams()
    const [charasteristicData, setCharasteristicData] = useState({
        id: '',
        icon: '',
        name: '',
    });

    const fetchData = async () => {
        try {
          const response = await fetch(`http://localhost:8080/v1/categories/1/providers/characteristics/${params.id}`);
          const charasteristics = await response.json();
          setCharasteristicData(prevcharasteristicData => ({
            ...prevcharasteristicData,
            id: charasteristics.id,
            icon: charasteristics.icon,
            name: charasteristics.name,
          })); 
        } catch (error) {
          console.error('Error al obtener los datos:', error);
        }
    };

    useEffect(() => {
        if (params.id) {
            fetchData();
        }
    }, []);

    const handleInputChange = (event) => {
        const { name, value } = event.target;
        setCharasteristicData((prevData) => ({
            ...prevData,
            [name]: value,
        }));
    };
    const handleSuccessfulSubmit = () => {
        setCharasteristicData({
            icon: '',
            name: '',
        });
    };
    const handleSubmit = async (event) => {
        const userToken = localStorage.getItem("user");
        const tokenObj = JSON.parse(userToken);
        const token = tokenObj.token; 
        
        event.preventDefault();
        const apiUrl = params.id
        ? `http://localhost:8080/v1/categories/1/providers/characteristics/${params.id}`
        : 'http://localhost:8080/v1/categories/1/providers/characteristics';
      
        try {
            const response = await fetch(apiUrl, {
                method: params.id ? 'PUT' : 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': `Bearer ${token}`
                },
                body: JSON.stringify(charasteristicData),
            });
            if (response.ok) {
                alert('Característica guardada exitosamente');
            } else {
                alert('Error al guardar característica');
            }
            handleSuccessfulSubmit()
        } catch (error) {
            console.error('Error de red:', error);
        }
    };

    return (
        <div className="formAdd__container">
            <h1 className='mb-5'>Nueva característica</h1>
            <form className="row g-3" onSubmit={handleSubmit}>
                <div className="col-md-6">
                    <label htmlFor="name" className="form-label">Nombre</label>
                    <input
                        type="text"
                        className="form-control"
                        id="name"
                        name="name"
                        value={charasteristicData.name}
                        onChange={handleInputChange}
                    />
                </div>
                <div className="col-6">
                    <label htmlFor="icon" className="form-label">
                        Seleccione icono
                    </label>
                    <select className="form-control" id='icon' name='icon' onChange={handleInputChange}>
                        <option selected disabled defaultValue="">Selecciona una opcion</option>
                        <option value="🌎">🌎</option>
                        <option value="💻">💻</option>
                        <option value="📚">📚</option>
                        <option value="👩🏻‍🤝‍🧑🏼">👩🏻‍🤝‍🧑🏼</option>
                    </select>
                </div>
                <div className="col-12">
                    <button type="submit" className="btn btn-primary">Guardar</button>
                </div>
            </form>
        </div>
    );
}

export default CharasteristicsForm;