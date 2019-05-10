package com.example.soccerstats.model;

import com.example.soccerstats.model.rounds.MatchData;
import com.example.soccerstats.model.standings.StandingsData;
import com.example.soccerstats.model.teams.TeamList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface SoccerApi {

    @GET("v1/leagues/{league}/seasons/{year}/teams") //Ex: league: liga, year:18-19
    Call<TeamList> getTeam(@Path("league")String league, @Path("year")String year);

    @GET("v1/leagues/{league}/seasons/{year}/standings")
    Call<StandingsData> getStandings(@Path("league")String league, @Path("year")String year);

    @GET("v1/leagues/{league}/seasons/{year}/rounds") //Ex: team_identifier=qtjxv9d71ntirsgpjbmeefda4gewdnd9
    Call<MatchData> getTeamStats(@Path("league")String league, @Path("year")String year, @Query("team_identifier")String teamIdentifier);
}
