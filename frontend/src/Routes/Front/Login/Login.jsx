import React from 'react'
import { Container } from 'react-bootstrap'
import LoginForm from '../../../Components/LoginForm/LoginForm'
import LoginBanner from '../../../assets/LoginBanner.svg'
import './Login.scss'
const Login = () => {
  return (
    <main id='login'>
        <Container>
            <section className='login-section'>
                <LoginForm/>
                <img src={LoginBanner} alt="Appkademy Login Banner" />
            </section>
        </Container>
    </main>
  )
}

export default Login