import React, { useState, useEffect } from 'react'
import './styles.scss'
import { Container } from 'react-bootstrap'
import BannerConfirm from '../../../assets/confirm.svg'
import { Link } from 'react-router-dom'

const Confirm = () => {
    return (
        <main>
            <Container>
            <Link to={'/'} >
                <img src={BannerConfirm} alt="Appkademy banner confirm" />
            </Link>
            </Container>
        </main>
    )
}

export default Confirm