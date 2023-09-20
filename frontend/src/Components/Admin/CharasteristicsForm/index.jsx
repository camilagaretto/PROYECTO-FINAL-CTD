import React, {useEffect, useState } from 'react';
import './styles.scss'

function CharasteristicsForm() {
    const [charasteristicData, setCharasteristicData] = useState({
        id: '',
        icon: '',
        name: '',
    });

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
        try {
            const response = await fetch('http://ec2-174-129-118-14.compute-1.amazonaws.com/v1/categories/1/providers/characteristics', {
                method: 'POST',
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