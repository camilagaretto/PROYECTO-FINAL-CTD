import React from 'react'
import './Footer.scss'
import DarkLogo from '../../assets/DarkLogo.svg'
import socials from '../../assets/socials.svg'

const Footer = () => {
  return (
    <footer id='footer'>
        <img src={DarkLogo} alt="Appkademy Dark Logo" />
        <p>© 2023 AppKademy Todos los derechos reservados.</p>
        <img src={socials} alt="Appkademy social media" />
    </footer>
  )
}

export default Footer