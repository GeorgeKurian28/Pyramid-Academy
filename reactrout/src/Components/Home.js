import React from 'react'
import Article from './Article' 
import Header from './Header'
import Navbar from './Navbar'
import Section from './Section'

const Home = () => {
  return (
    <>
    <Navbar/>
    <h1>Home</h1>
    <Header/>
    <Article/>
    <Section/>
    </>
  )
}

export default Home