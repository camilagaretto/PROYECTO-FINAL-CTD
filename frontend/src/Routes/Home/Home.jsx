import React from 'react'
import Container from 'react-bootstrap/Container';
import { AnimatePresence, motion } from 'framer-motion';
import { useEffect, useState } from 'react';
import Filter from '../../Components/Filter/Filter';
import CardProduct from '../../Components/Card/Card';
import Search from '../../Components/Search/Search';
import Banner from "../../assets/banner-home.svg";
import Step1 from "../../assets/step-1.svg"
import Step2 from "../../assets/step-2.svg"
import Step3 from "../../assets/step-3.svg"
import Num1 from "../../assets/01.svg"
import Num2 from "../../assets/02.svg"
import Num3 from "../../assets/03.svg"
import './Home.scss'
import Steps from '../../Components/Steps/Steps';

const Home = () => {
  const [popular, setPopular] = useState([]);
  const [filtered, setFiltered] = useState([]);
  const [activeGenre, setActiveGenre] = useState(0);

  const fetchPopular = async () => {   
    const response = await fetch("https://api.themoviedb.org/3/movie/popular?api_key=1af8f5a0dac921ed793eaf9b1a89b23e&language=en-US&page=1");
    const movies = await response.json();
    setPopular(movies.results);
    setFiltered(movies.results);
  }

  useEffect(() => {
    fetchPopular();
  }, []);

  return (
    <main id='home'>
      <Container>
        <section className='container-main-banner'>
          <div className='banner-description'>
            <h1>Descubre tu nueva pasión</h1>
            <Search/>
          </div>
          <img src={Banner} alt='Appkademy home banner'/>
        </section>
        <section className='steps-container'>
          <Steps num={Num1} img={Step1} title="Busca" description="Busca sin limites al profesor que se adapte a tus necesidades"/>
          <Steps num={Num2} img={Step2} title="Contacta" description="Nuestros docentes se encargaran de responder a tu solicitud"/>
          <Steps num={Num3} img={Step3} title="Estudia" description="Acuerda una fecha, hora y tarifa, con todo esto estamos listos"/>
        </section>
        <section>
          <h2>¿Qué quieres aprender hoy?</h2>
          <Filter 
            popular={popular}
            setFiltered={setFiltered}
            activeGenre={activeGenre}
            setActiveGenre={setActiveGenre}
          />
          <motion.div
            layout 
            className="grid-container"
          >
            <AnimatePresence>
              {filtered.map(movie => (
                <CardProduct 
                  key={movie.id} 
                  movie = {movie}
                />
              ))}
            </AnimatePresence>
          </motion.div>
        </section>
      </Container>
    </main>
  )
}

export default Home