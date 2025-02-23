import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DashboardComponent } from './pages/dashboard/dashboard.component';

// ROUTE PATHS
const routes: Routes = [
  { path: '', redirectTo: '/getAlltask', pathMatch: 'full' },
  { path: 'getAlltask', component: DashboardComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
