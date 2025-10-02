# CardCatcher - Authentication System

## Features Implemented

✅ **User Registration** - Create new accounts with email and password
✅ **User Login** - Secure authentication with Supabase
✅ **Password Reset** - Forgot password functionality
✅ **User Dashboard** - Profile page showing user information
✅ **Secure Logout** - Sign out functionality
✅ **Session Management** - Automatic state updates
✅ **Form Validation** - Client-side validation for all inputs
✅ **Toast Notifications** - User feedback for all actions

## Technology Stack

- **Frontend**: Vue 3 + Quasar Framework
- **Backend**: Supabase (Authentication & Database)
- **Build Tool**: Vite
- **Language**: JavaScript

## Project Structure

```
frontend/CardCatcher/
├── src/
│   ├── components/
│   │   ├── AuthCard.vue        # Login/Signup form
│   │   └── Dashboard.vue       # User profile dashboard
│   ├── composables/
│   │   └── useAuth.js          # Authentication logic
│   ├── lib/
│   │   └── supabase.js         # Supabase client
│   ├── App.vue                 # Main application
│   └── main.js                 # Application entry point
├── .env                        # Environment variables (Supabase config)
└── package.json
```

## How to Use

1. **Sign Up**: Click "Sign Up" and enter your name, email, and password
2. **Sign In**: Enter your email and password to log in
3. **Forgot Password**: Enter your email and click "Forgot password?" to receive a reset link
4. **Dashboard**: After login, you'll see your profile with account information
5. **Sign Out**: Click the "Sign Out" button to log out

## Environment Variables

The `.env` file contains the Supabase configuration:
- `VITE_SUPABASE_URL` - Your Supabase project URL
- `VITE_SUPABASE_ANON_KEY` - Your Supabase anonymous key

## Running the Application

```bash
# Install dependencies
npm install

# Run development server
npm run dev

# Build for production
npm run build
```

## Security Features

- Passwords are securely hashed by Supabase
- JWT-based session management
- Automatic session refresh
- Protected routes (Dashboard only visible when authenticated)
- Email validation
- Password strength requirements (minimum 6 characters)

## API Integration

All authentication is handled through Supabase Auth:
- `signUp()` - Create new user accounts
- `signInWithPassword()` - Authenticate users
- `signOut()` - End user sessions
- `resetPasswordForEmail()` - Send password reset emails
- `onAuthStateChange()` - Listen for authentication state changes

## Notes

- Email confirmation is disabled by default
- The application uses Supabase's built-in `auth.users` table
- All user data is stored securely in Supabase
- Real-time authentication state updates across the application
