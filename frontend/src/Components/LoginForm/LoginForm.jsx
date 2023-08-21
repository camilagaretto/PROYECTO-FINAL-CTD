import React from 'react'
import { useFormik } from 'formik'
import './LoginForm.scss'
import { Link } from 'react-router-dom'
import { LoginSchema } from '../../Schemas/Schemas'

const LoginForm = () => {

  const { handleChange, handleSubmit, errors } = useFormik({
    initialValues: {
      email: "",
      password: "",
    },
    onSubmit: (values) => {
      console.log(values);
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
        </div>
      </form>
    </div>
  )
}

export default LoginForm