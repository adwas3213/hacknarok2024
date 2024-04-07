import React from 'react';
import './Home.scss';
import { Avatar, Fab } from '@mui/material';
import PersonIcon from '@mui/icons-material/Person';
import AutoAwesomeIcon from '@mui/icons-material/AutoAwesome';
import { useGetPostsQuery } from 'features/server/defaultApi';
import { useNavigate } from 'react-router';
import Logo from 'components/Logo';
import Post from './Post';
import YoutubeSearchedForIcon from '@mui/icons-material/YoutubeSearchedFor';
import NotificationImportantIcon from '@mui/icons-material/NotificationImportant';

export default function Home() {
  const {data: posts } = useGetPostsQuery();
  const navigate = useNavigate();

  return (
    <div className="home">
      <div className="header">
        <Logo />
        <nav className="nav">
          <Fab
            variant="extended"
            color="default"
            size="large"
          >
            REVIEW <YoutubeSearchedForIcon />
          </Fab>
          <Fab
            variant="extended"
            color="default"
            size="large"
          >
            GET TOPIC <NotificationImportantIcon />
          </Fab>
          <Fab
            variant="extended"
            color="default"
            size="large"
            onClick={() => navigate('/generate')}
          >
            GENERATE NEW POST <AutoAwesomeIcon />
          </Fab>
          <Avatar alt="Avatar" variant="circular" className="avatar">
            <PersonIcon />
          </Avatar>
        </nav>
      </div>
      <div className="posts">
        {
          posts?.map((post) => (
            <Post key={post.id} post={post} />
          ))
        }
      </div>
    </div>
  );
}