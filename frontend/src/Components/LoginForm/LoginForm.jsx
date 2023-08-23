import React, { useState } from 'react'
import { useFormik } from 'formik'
import './LoginForm.scss'
import { Link, useNavigate } from 'react-router-dom'
import { LoginSchema } from '../../Schemas/Schemas'
import { useAuth } from '../../Context/AuthContext'

const LoginForm = () => {

  const [credentialError, setCredentialError] = useState("")
  const { login } = useAuth()

  const navigate = useNavigate()

  const handleSubmitForm = async (values) => {
    try {
      const response = await fetch('http://localhost:8080/v1/auth/authenticate', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(values),
      });
      if (response.ok) {
        alert('Login exitoso');
        const data = await response.json();
        const userData = {
          userId: data.userId,
          token: data.token,
          isAdmin: data.isAdmin,
          userType: data.userType,
          userTypeId: data.userTypeId,
          email : data.email
        };

        // Realizar la segunda solicitud después del inicio de sesión
        const {userTypeId, token} = userData
        const getStudent = await fetch(
          `http://localhost:8080/v1/categories/1/customers/${userTypeId}`, {
          method: 'GET',
          headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`,
          }
        });
        if (getStudent.ok) {
          const userInformation = await getStudent.json();
          const {firstName, lastName} = userInformation
          userData.firstName = firstName
          userData.lastName = lastName
        }

        const userDataJSON = JSON.stringify(userData);
        localStorage.setItem('user', userDataJSON);
        login(userData)

        navigate('/')
      } else {
        setCredentialError("Credenciales invalidas");
      }
    } catch (error) {
      console.error('Error de red:', error);
    }
  }

  const { handleChange, handleSubmit, errors } = useFormik({
    initialValues: {
      email: "",
      password: "",
    },
    onSubmit: (values) => {
      handleSubmitForm(values)
    },
    validationSchema: LoginSchema
  })

  return (
    <div className='login-form-container'>
      <h1 className='mb-5'>Bienvenid@</h1>
      <form className="row g-3 login-form" onSubmit={handleSubmit}>
        <div className="col-12">
          <label htmlFor="email" className="form-label">Email</label>
          <input
            onChange={handleChange}
            type="email"
            className="form-control"
            id="email"
            name="email"
          />
        </div>
        <div className="col-12">
          <label htmlFor="password" className="form-label">Contraseña</label>
          <input
            onChange={handleChange}
            type="password"
            className="form-control"
            id="password"
            name="password"
          />
        </div>
        <div className="col-12 btn-register">
          <button type="submit" className="btn btn-dark">Iniciar Sesion</button>
          <p>No tienes cuenta? <Link to="/register">Registrate</Link> </p>
        </div>
        <div>
          {errors.email && <p className='error'>{errors.email}</p>}
          {errors.password && <p className='error'>{errors.password}</p>}
          {credentialError && <p className='error'>{credentialError}</p>}
        </div>
      </form>
    </div>
  )
}

export default LoginForm