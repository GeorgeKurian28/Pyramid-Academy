import {Routes, Route} from 'react-router-dom'
import Contact from './Components/Contact';
import Home from './Components/Home';


function App() {
  return (
    <Routes>
      <Route path = "/" element ={<Home/>}></Route>
      <Route path = "contact" element = {<Contact/>}></Route>
    </Routes>
  );
}

export default App;
