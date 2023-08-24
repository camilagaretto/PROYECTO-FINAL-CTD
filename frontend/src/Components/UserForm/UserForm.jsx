import React, { useState } from 'react'
import './UserForm.scss'
import { useFormik } from 'formik'
import { Link, useNavigate } from 'react-router-dom'
import { RegisterSchema } from '../../Schemas/Schemas'


const UserForm = () => {

  const navigate = useNavigate()

  const [error, setError] = useState("")

  const handleSubmitForm = async (values) => {

    const userData = {
      email: values.email,
      password: values.password,
    };

    try {
      const response = await fetch('http://localhost:8080/v1/auth/register', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(userData),
      });
      if (response.ok) {
        // alert('Usuario creado exitosamente');
        const data = await response.json();
        const userId = data.userId;
        const token = data.token;
        createStudent(userId, values, token);
      } else {
        const res = await response.json()
        const error = res.cause?.split("_").join(" ")
        if(error){
          setError(error)
        }else{
          setError('Error al crear usuario, revise todos los campos o intente mas tarde.')
        }
      }
    } catch (error) {
      alert('error al crear usuario')
      console.error('Error de red:', error);
      console.log(error)
    }
  };

  // Función para crear un estudiante
  const createStudent = async (userId, values, token) => {
    const studentData = {
      userId: userId,
      firstName: values.firstName,
      lastName: values.lastName,
      email: values.email,
      scheduledAppointments: [],
    };

    try {
      const response = await fetch('http://localhost:8080/v1/categories/1/customers/', {
        method: 'POST',
        mode: 'cors',
        headers: {
          'Content-Type': 'application/json',
          'Authorization': `Bearer ${token}`,
        },
        body: JSON.stringify(studentData),
      });
      if (response.ok) {
        alert('Estudiante creado exitosamente');
        navigate('/login')
      } else {
        const error = await response.json();  
        console.log(error.cause);
        alert(error.cause);
      }
    } catch (error) {
      console.log(error)
      console.error('Error de red:', error);
    }
  };

  const { handleSubmit, handleChange, errors } = useFormik({
    initialValues: {
      firstName: '',
      lastName: '',
      email: '',
      password: '',
      confirmPassword: ''
    },
    onSubmit: handleSubmitForm,
    validationSchema: RegisterSchema
  })

  return (
    <div className="userAdd__container">
      <h1 className='mb-5'>List@ para Aprender?</h1>
      <form className="row g-3" onSubmit={handleSubmit}>
        <div className="col-md-6">
          <label htmlFor="firstName" className="form-label">Nombre</label>
          <input
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
            onChange={handleChange}
            type="email"
            className="form-control"
            id="email"
            name="email"
          />
        </div>
        <div className="col-md-6">
          <label htmlFor="password" className="form-label">Contraseña</label>
          <input
            onChange={handleChange}
            type="password"
            className="form-control"
            id="password"
            name="password"
          />
        </div>
        <div className="col-md-6">
          <label htmlFor="confirmPassword" className="form-label">Confirmar Contraseña</label>
          <input
            onChange={handleChange}
            type="password"
            className="form-control"
            id="confirmPassword"
            name="confirmPassword"
          />
        </div>
        <div className="col-12 btn-register">
          <button type="submit" className="btn btn-dark">Registrarse</button>
          <p>Ya tiene cuenta? <Link to="/login">Inicie sesion</Link> </p>
        </div>
        <div className='col-12'>
          {errors.firstName && <p className='error'>{errors.firstName}</p>}
          {errors.lastName && <p className='error'>{errors.lastName}</p>}
          {errors.email && <p className='error'>{errors.email}</p>}
          {errors.password && <p className='error'>{errors.password}</p>}
          {errors.confirmPassword && <p className='error'>{errors.confirmPassword}</p>}
          {error && <p className='error'>{error}</p>}
        </div>
      </form>
    </div>
  )
}

export default UserForm