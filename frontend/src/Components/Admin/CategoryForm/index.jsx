import React, {useEffect, useState } from 'react';
import './styles.scss'

function CategoryForm() {
    const [categoryData, setCategoryData] = useState({
        name: '',
    });

    const handleInputChange = (event) => {
        const { name, value } = event.target;
        setCategoryData((prevData) => ({
            ...prevData,
            [name]: value,
        }));
    };
    const handleSuccessfulSubmit = () => {
        setCategoryData({
            name: '',
        });
    };
    const handleSubmit = async (event) => {
        const userToken = localStorage.getItem("user");
        const tokenObj = JSON.parse(userToken);
        const token = tokenObj.token; 
        
        event.preventDefault();

        try {
            const response = await fetch('http://localhost:8080/v1/categories/1/providers/teaching_subject', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': `Bearer ${token}`
                },
                body: JSON.stringify(categoryData),
            });
            if (response.ok) {
                alert('Categoría guardada exitosamente');
            } else {
                alert('Error al guardar categoría');
            }
            handleSuccessfulSubmit()
        } catch (error) {
            console.error('Error de red:', error);
        }
    };

    return (
        <div className="formAdd__container">
            <h1 className='mb-5'>Nueva categoría</h1>
            <form className="row g-3" onSubmit={handleSubmit}>
                <div className="col-md-12">
                    <label htmlFor="name" className="form-label">Nombre</label>
                    <input
                        type="text"
                        className="form-control"
                        id="name"
                        name="name"
                        value={categoryData.name}
                        onChange={handleInputChange}
                    />
                </div>
                <div className="col-12">
                    <button type="submit" className="btn btn-primary">Guardar</button>
                </div>
            </form>
        </div>
    );
}

export default CategoryForm;