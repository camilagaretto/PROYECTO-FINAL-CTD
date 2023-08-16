import React from 'react'
import './UserForm.scss'

const UserForm = () => {
    return (
        <div className="userAdd__container">
            <h1 className='mb-5'>List@ para Aprender?</h1>
            <form className="row g-3">
                <div className="col-md-6">
                    <label htmlFor="firstName" className="form-label">Nombre</label>
                    <input
                        type="text"
                        className="form-control"
                        id="firstName"
                        name="firstName"
                    />
                </div>
                <div className="col-md-6">
                    <label htmlFor="lastName" className="form-label">Apellido</label>
                    <input
                        type="text"
                        className="form-control"
                        id="lastName"
                        name="lastName"
                    />
                </div>
                <div className="col-12">
                    <label htmlFor="email" className="form-label">Correo</label>
                    <input
                        type="email"
                        className="form-control"
                        id="email"
                        name="email"
                    />
                </div>
                <div className="col-md-6">
                    <label htmlFor="phone" className="form-label">Telefono</label>
                    <input
                        type="text"
                        className="form-control"
                        id="phone"
                        name="phone"
                    />
                </div>
                <div className='col-md-6'></div>
                <div className="col-md-6">
                    <label htmlFor="country" className="form-label">País</label>
                    <select
                        className="form-select"
                        id="country"
                        name="country"
                    >
                        <option value="">--</option>
                        <option value="ARGENTINA">Argentina</option>
                    </select>
                </div>
                <div className="col-md-6">
                    <label htmlFor="province" className="form-label">Provincia</label>
                    <select
                        className="form-select"
                        id="province"
                        name="province"
                    >
                        <option value="">--</option>
                        <option value="CIUDAD_DE_BUENOS_AIRES">Ciudad de Buenos Aires</option>
                        <option value="SANTA_FE">Santa Fe</option>
                        <option value="BUENOS_AIRES">Buenos Aires</option>
                    </select>
                </div>
                <div className="col-md-6">
                    <label htmlFor="streetName" className="form-label">Calle</label>
                    <input
                        type="text"
                        className="form-control"
                        id="streetName"
                        name="streetName"
                    />
                </div>
                <div className="col-md-6">
                    <label htmlFor="streetNumber" className="form-label">Nro. Calle</label>
                    <input
                        type="text"
                        className="form-control"
                        id="streetNumber"
                        name="streetNumber"                 
                    />
                </div>
                <div className="col-md-6">
                    <label htmlFor="password" className="form-label">Contraseña</label>
                    <input
                        type="password"
                        className="form-control"
                        id="password"
                        name="password"                 
                    />
                </div>
                <div className="col-md-6">
                    <label htmlFor="confirmPassword" className="form-label">Confirmar Contraseña</label>
                    <input
                        type="password"
                        className="form-control"
                        id="confirmPassword"
                        name="confirmPassword"                 
                    />
                </div>
                <div className="col-12">
                    <button type="submit" className="btn btn-primary">Registrarse</button>
                </div>
            </form>
        </div>
    )
}

export default UserForm