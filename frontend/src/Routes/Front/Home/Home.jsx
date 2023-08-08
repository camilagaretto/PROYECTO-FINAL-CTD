import React from 'react'
import Container from 'react-bootstrap/Container';
import { AnimatePresence, motion } from 'framer-motion';
import { useEffect, useState } from 'react';
import Filter from '../../../Components/Filter/Filter';
import CardProduct from '../../../Components/Card/Card';
import Search from '../../../Components/Search/Search';
import PaperPlane from '../../../assets/Paper-Plane.png'
import BannerProfes from '../../../assets/banner-profes.svg'
import './Home.css'
import { Link } from 'react-router-dom';

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
            <h1>Encuentra tu nueva pasión</h1>
            <Search />
          </div>
        </section>

        <section>
          <div className='container-aprender'>
            <div>
              <h2>¿Qué te gustaría aprender hoy?</h2>
              <p>Te dejamos los tags para que lo encuentres mucho mas rapido</p>
            </div>
            <Link to="/" className='container-aprender-ver'>Ver Listado Completo</Link>
          </div>
          
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
                  movie={movie}
                />
              ))}
            </AnimatePresence>
          </motion.div>
          <div className='mostrar-container'>
            <p>No encontraste a tu profe?</p>
            <Link className='btn btn-primary'>
              <img className='paper-plane' src={PaperPlane} alt="Avion de papel" />
              Mostrar Todos
            </Link>
          </div>
        </section>

        <section className='container-profes'>
          <img src={BannerProfes} alt="Appkademy banner profesores" />
          <div>
            <h3>Quieres unirte al equipo de profes?</h3>
            <Link to='/' className='btn btn-profesores'>Llenar Formulario</Link>
          </div>
        </section>

      </Container>
    </main>
  )
}

export default Home