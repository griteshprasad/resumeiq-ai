import { Routes } from '@angular/router';
import { authGuard } from './core/guards/auth-guard';
import { guestGuard } from './core/guards/guest-guard';

export const routes: Routes = [
   {
    path: '',
    pathMatch: 'full',
    redirectTo: 'dashboard'
  },
  {
    path: '',
    canActivate: [authGuard],
    loadComponent: () => import('./layout/layouts/main-layout/main-layout').then(m => m.MainLayout),
    children: [
      {
        path: 'dashboard',
        loadComponent: () => import('./features/dashboard/pages/dashboard/dashboard').then(m => m.Dashboard),
      },
      {
        path: 'resumes',
        loadComponent: () => import('./features/resume/pages/resume-list/resume-list')
            .then(m => m.ResumeList)
      },
      {
        path: 'job-descriptions',
        loadComponent: () => import('./features/job-description/pages/job-description-list/job-description-list').then(m => m.JobDescriptionList)
      },
      {
        path: 'analysis',
        loadComponent: () => import('./features/analysis/pages/analysis-result/analysis-result').then(m => m.AnalysisResult)
      },
      {
        path: 'rewrite',
        loadComponent: () => import('./features/resume-rewrite/pages/resume-rewrite/resume-rewrite').then(m => m.ResumeRewrite)
      }
    ]
  },
  {
    path: 'login',
    loadComponent: () => import('./features/auth/pages/login/login').then(m => m.Login),
    canActivate: [guestGuard]
  },
  {
    path: 'register',
    loadComponent: () => import('./features/auth/pages/register/register').then(m => m.Register),
    canActivate: [guestGuard]
  },
  {
    path: '**',
    redirectTo: 'dashboard'
  }
];