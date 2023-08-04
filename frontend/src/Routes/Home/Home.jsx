import React from 'react'
import Container from 'react-bootstrap/Container';
import { AnimatePresence, motion } from 'framer-motion';
import { useEffect, useState } from 'react';
import Filter from '../../Components/Filter/Filter';
import CardProduct from '../../Components/Card/Card';
import Search from '../../Components/Search/Search';
import Banner from "../../assets/banner-home.svg";
import './Home.css'

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
          <img src={Banner}/>
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