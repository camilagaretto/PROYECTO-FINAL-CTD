import React, { useEffect, useState } from 'react'
import './UserForm.scss'
import { useFormik } from 'formik'
import { Link, useNavigate, useParams } from 'react-router-dom'
// import { UpdateSchema } from '../../Schemas/Schemas'


const UserUpdateForm = () => {

    const {id} = useParams()

    const [userFirstName, setUserFirstName] = useState("")
    const [userLastName, setUserLastName] = useState("")
    const [userEmail, setUserEmail] = useState("")
    const [token, setToken] = useState("")

    useEffect(()=>{
        const userDataJSON = localStorage.getItem('user');
        if (userDataJSON) {
          const userData = JSON.parse(userDataJSON);
          setUserFirstName(userData.firstName);
          setUserLastName(userData.lastName);
          setUserEmail(userData.email)
          setToken(userData.token)
        }
    },[id])

    const { handleChange, handleSubmit, errors, values } = useFormik({
        initialValues: {
            firstName: userFirstName,
            lastName: userLastName,
            email: userEmail,
        },
        onSubmit : (values) => {
            //Logica de edicion de datos
        },
        enableReinitialize: true,
    })

    return (
        <div className="userUpdate__container">
            <h1 className='mb-5'>Hola, {`${userFirstName}`}!</h1>
            <form className="row g-3" onSubmit={handleSubmit}>
                <div className="col-md-6">
                    <label htmlFor="firstName" className="form-label">Nombre</label>
                    <input
                        value={values.firstName}
                        onChange={handleChange}
                        type="text"
                        className="form-control"
                        id="firstName"
                        name="firstName"
                    />
                </div>
                <div className="col-md-6">
                    <label htmlFor="lastName" className="form-label">Apellido</label>
                    <input
                        value={values.lastName}
                        onChange={handleChange}
                        type="text"
                        className="form-control"
                        id="lastName"
                        name="lastName"
                    />
                </div>
                <div className="col-12">
                    <label htmlFor="email" className="form-label">Correo electronico</label>
                    <input
                        value={values.email}
                        onChange={handleChange}
                        type="email"
                        className="form-control"
                        id="email"
                        name="email"
                    />
                </div>
                <div className="col-12 btn-register">
                    <button type="submit" className="btn btn-dark">Editar</button>
                </div>
                <div className='col-12'>
                    {errors.firstName && <p className='error'>{errors.firstName}</p>}
                    {errors.lastName && <p className='error'>{errors.lastName}</p>}
                    {errors.email && <p className='error'>{errors.email}</p>}
                    {errors.password && <p className='error'>{errors.password}</p>}
                </div>
            </form>
        </div>
    )
}

export default UserUpdateForm