import React, {useState, useEffect} from 'react'
import { useParams } from 'react-router-dom'
import CardDetail from '../../../Components/CardDetail/CardDetail'
import './TeacherDetail.scss'
import { Container } from 'react-bootstrap'
import { Link } from 'react-router-dom'
import Reserva from '../../../assets/Reserva.png'
import Galery from '../../../assets/Detail-photos.svg'
import Itinerario from '../../../assets/Itinerario.svg'
import Certificado from '../../../assets/certificado.svg'

const TeacherDetail = () => {
    const params = useParams()
    const [teacherData, setTeacherData] = useState([]);
    const [hourlyRatesArray, setHourlyRatesArray] = useState([]);
    useEffect(() => {
        const fetchData = async () => {
          try {
            const response = await fetch(`http://ec2-107-21-139-55.compute-1.amazonaws.com/v1/categories/1/providers/${params.id}`);
            const data = await response.json();
            const hourlyRatesArray = Object.entries(data.hourlyRates).map(([currency, value]) => ({
                currency,
                value,
            }));
            setHourlyRatesArray(hourlyRatesArray)
            setTeacherData(data);
          } catch (error) {
            console.error('Error al obtener los datos:', error);
          }
        };
    
        fetchData();
    }, []);
    return (
        <main>
            <Container className='detail-container'>
                <section className='detail-left'>
                    <div className='sticky'>
                        <CardDetail 
                        fullName={teacherData.firstName + ' ' + teacherData.lastName}
                        hourlyRates={hourlyRatesArray}
                        characteristics={teacherData.characteristics}
                        profileImg={teacherData.profilePictureUrl}
                        />
                        <div className='descripcion'>
                            <h2>Descripción</h2>
                            <p>{teacherData.fullDescription}</p>
                        </div>
                    </div>
                </section>
                <section className='detail-right'>
                    <h4>Appkademy</h4>
                    <h1><span>Obtiene</span> los <br /> conocimientos de <span>un Experto</span></h1>
                    <p>{teacherData.shortDescription}</p>
                    <div>
                        <hr />
                        <Link className='btn btn-dark'><img className='reserva-img' src={Reserva} alt="Appkademy reservation vector" />Reserva</Link>
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
                            <h1>Mas sobre {teacherData.firstName} {teacherData.lastName}</h1>
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