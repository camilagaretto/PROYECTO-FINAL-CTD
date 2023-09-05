import { useState, useEffect } from 'react';
import Container from 'react-bootstrap/Container';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';
import { Link, useNavigate } from 'react-router-dom';
import AppkademyLogo from "../../assets/Logo.svg";
import './Navbar.scss'
import { useAuth } from '../../Context/AuthContext';

function NavScrollExample() {

    const { isLoggedIn, isAdmin, logout, login } = useAuth()
    const [initials, setInitials] = useState("")
    const [fullName, setFullName] = useState("")
    const [id, setId] = useState()
    const navigate = useNavigate()

    useEffect(() => {
        const userDataJSON = localStorage.getItem('user');
        if (userDataJSON) {
            const userData = JSON.parse(userDataJSON);
            if (userData.userType != 'ADMIN') {
                const { firstName, lastName, userTypeId } = userData;
                const fullname = `${firstName} ${lastName}`
                const initials = `${firstName[0]?.toUpperCase()}${lastName[0]?.toUpperCase()}`
                setInitials(initials)
                setFullName(fullname)
                setId(userTypeId)
            }
        } else {
            console.log('No se encontraron datos de usuario en el localStorage');
        }
    }, [isLoggedIn])

    useEffect(() => {
        const userDataJSON = localStorage.getItem('user');
        if (userDataJSON) {
            const userData = JSON.parse(userDataJSON);
            login(userData)
        }
    }, [])

    const handleLogOut = () => {
        logout()
        navigate("/")
    }

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
                                    <Link className='navbar__link-secondary' to={`user/${id}/favourites`}>Favoritos</Link>
                                    {!isAdmin &&
                                     <Link className='user-name' to={`/user/${id}`}>
                                        <div className='user-logo'><p>{initials}</p></div>
                                        <p>{fullName}</p>
                                     </Link>
                                     }
                                    {isAdmin && <Link className='navbar__link-secondary' to="/admin">Admin</Link>}
                                    <button onClick={handleLogOut} className='btn btn-dark'>Cerrar Sesión</button>
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