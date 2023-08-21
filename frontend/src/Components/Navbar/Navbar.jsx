import { useState, useEffect } from 'react';
import Container from 'react-bootstrap/Container';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';
import { Link } from 'react-router-dom';
import AppkademyLogo from "../../assets/Logo.svg";
import './Navbar.scss'
import { useAuth } from '../../Context/AuthContext';

function NavScrollExample() {

    const {isLoggedIn, isAdmin, logout} = useAuth()
    
    return (
        <header>
            <Navbar expand="lg" className={`fixed-top navbar-white`}>
                <Container fluid>
                    <Link to="/"><Navbar.Brand><img className='navbar__logo' src={AppkademyLogo} alt="Appkademy Logo" /></Navbar.Brand></Link>
                    <Navbar.Toggle aria-controls="navbarScroll" />
                    <Navbar.Collapse id="navbarScroll">
                        <Nav
                            className="me-auto my-2 my-lg-0"
                            style={{ maxHeight: '100px' }}
                            navbarScroll
                        />
                        <Nav className="d-flex navbar__links__flex">
                            {isLoggedIn ? (
                                <>
                                    {isAdmin && <Link className='navbar__link-secondary' to="/admin">Admin</Link>}
                                    <button onClick={logout} className='btn btn-dark'>Cerrar Sesión</button>
                                </>
                            ) : (
                                <>
                                    <Link className='navbar__link-secondary' to="/login">Iniciar Sesión</Link>
                                    <Link className='navbar__link-primary' to="/register">Crear cuenta</Link>
                                </>
                            )}
                        </Nav>
                    </Navbar.Collapse>
                </Container>
            </Navbar>
        </header>
    );
}

export default NavScrollExample;