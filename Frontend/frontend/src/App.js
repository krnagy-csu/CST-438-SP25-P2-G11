import logo from './logo.svg';
import './App.css';
import { BrowserRouter as Router, Route, Routes, Link } from "react-router-dom";
import Home from "./pages/Home";
import Tierlist from "./pages/Tierlist";

function App() {
  return (
    <Router>
    <Routes>
      <Route path="/" element={<Home />} />
      <Route path="/tierlist" element={<Tierlist />} />
    </Routes>
  </Router>
  );
}

export default App;
