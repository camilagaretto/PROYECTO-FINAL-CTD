import React from 'react'
import CardDetail from '../../../Components/CardDetail/CardDetail'
import './TeacherDetail.scss'
import { Container } from 'react-bootstrap'
import { Link } from 'react-router-dom'
import Reserva from '../../../assets/Reserva.png'
import Galery from '../../../assets/Detail-photos.svg'
import Itinerario from '../../../assets/Itinerario.svg'
import Certificado from '../../../assets/certificado.svg'

const TeacherDetail = () => {
    return (
        <main>
            <Container className='detail-container'>
                <section className='detail-left'>
                    <CardDetail />
                    <div className='descripcion'>
                        <h2>Descripción</h2>
                        <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Error, repellendus. Numquam expedita officiis quo harum deleniti magnam ipsa incidunt praesentium velit. Harum necessitatibus fugiat facere nostrum debitis laudantium, fugit voluptate.</p>
                    </div>
                </section>
                <section className='detail-right'>
                    <h4>Algebra Relacional</h4>
                    <h1><span>Obtiene</span> los <br /> conocimientos de <span>un Experto</span></h1>
                    <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Accusantium quod, illum quam unde delectus officiis qui nesciunt quia animi, excepturi fuga deserunt, provident.</p>
                    <div>
                        <hr />
                        <Link className='btn btn-dark'><img src={Reserva} alt="Appkademy reservation vector" />Reserva</Link>
                    </div>

                    {/* **** GALERIA **** */}
                    <div className='galery-section'>
                        <h1>Coordina una clase de prueba</h1>
                        <p>Te presentamos algunas opciones de encuentro, tanto virtual como presencial.</p>
                        <div>
                            <img src={Galery} alt="Appkademy teacher gallery" />
                            <img className='itinerario' src={Itinerario} alt="Appkademy itinerario" />
                        </div>
                        <div className='more-information'>
                            <h1>Mas sobre Ecuacio</h1>
                            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce varius faucibus massa sollicitudin amet augue. Nibh metus a semper purus mauris duis. Lorem eu neque, tristique quis duis. Nibh scelerisque ac adipiscing velit non nulla in amet pellentesque.Sit turpis pretium eget maecenas. Vestibulum dolor mattis consectetur eget commodo vitae. Amet pellentesque sit pulvinar lorem mi a, euismod risus r.</p>
                            <div>
                                <img src={Certificado} alt="" />
                            </div>
                        </div>
                    </div>

                </section>
            </Container>
        </main>
    )
}

export default TeacherDetail