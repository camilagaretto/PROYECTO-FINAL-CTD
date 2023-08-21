import React from 'react'
import { Container } from 'react-bootstrap'
import LoginForm from '../../../Components/LoginForm/LoginForm'

const Login = () => {
  return (
    <main>
        <Container>
            <section>
                <LoginForm/>
            </section>
        </Container>
    </main>
  )
}

export default Login