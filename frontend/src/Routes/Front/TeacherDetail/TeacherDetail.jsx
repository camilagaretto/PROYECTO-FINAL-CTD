import React, { useState, useEffect } from 'react'
import { useParams } from 'react-router-dom'
import CardDetail from '../../../Components/CardDetail/CardDetail'
import './TeacherDetail.scss'
import { Container } from 'react-bootstrap'
import { Link, useLocation } from 'react-router-dom'
import Reserva from '../../../assets/Reserva.png'
import Galery from '../../../assets/Detail-photos.svg'
import Certificado from '../../../assets/certificado.svg'
import Appointments from '../../../Components/Appointments';
import { terms } from '../../../terms'
import TermCard from '../../../Components/Terms/TermCard'
import { WhatsappShareButton, WhatsappIcon } from 'react-share'

const TeacherDetail = () => {
    const params = useParams()
    const [teacherData, setTeacherData] = useState([]);
    const [hourlyRatesArray, setHourlyRatesArray] = useState([])
    const location = useLocation()
    const [url, setUrl] = useState("")

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
                            <WhatsappShareButton url={`http://appkademy.s3-website-us-east-1.amazonaws.com${url}`} title={`Aprende con ${teacherData.firstName}!`}>
                                <WhatsappIcon size={40} round={true} />
                            </WhatsappShareButton>
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
                        </div>
                        <Appointments teacherId={teacherData.id} events={teacherData.scheduledAppointments} weeklyWorkingSchedule={teacherData.weeklyWorkingSchedule} />
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
            <section className='terms'>
                <h2>Nuestras Politicas</h2>
                <div className='terms-container'>
                    {
                        terms.map(term => (
                            <TermCard key={term.id} image={term.image} title={term.title} description={term.description} />
                        ))
                    }
                </div>
            </section>
        </main>
    )
}

export default TeacherDetail
