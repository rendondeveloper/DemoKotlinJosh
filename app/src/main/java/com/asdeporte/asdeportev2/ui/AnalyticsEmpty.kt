package com.asdeporte.asdeportev2.ui
/*
import android.app.Activity
import com.sportmaniac.core_virtual.service.analytics.CVAnalyticsService
import com.sportmaniac.view_commons.model.FeedbackReport
import com.sportmaniac.view_commons.service.AnalyticsService
import com.sportmaniac.view_virtual.service.VVAnalyticsService

class AnalyticsEmpty : AnalyticsService, com.sportmaniac.view_live.service.analytics.AnalyticsService, com.sportmaniac.view_rankings.service.AnalyticsService,
    VVAnalyticsService, CVAnalyticsService {

    override fun getToken(): String? {
        return ""
    }

    override fun eventChange(s: String?) {}

    override fun categoryChange(s: String?) {}

    override fun eventSeeAthlete(s: String?) {}

    override fun tapSearchAthlete(s: String?) {}

    override fun detailAthleteTiming() {}

    override fun detailAthleteRanking() {}

    override fun detailAthleteVideoRep(s: String?) {}

    override fun statisticsParticipation(s: String?) {}

    override fun statisticsAverages(s: String?) {}

    override fun statisticsIntervals() {}

    override fun galleryPhotoOpen() {}

    override fun screenRankingHome(activity: Activity?) {}

    override fun screenRankingClub(activity: Activity?) {}

    override fun screenRankingEventChanger(activity: Activity?) {}

    override fun screenRankingSearch(activity: Activity?) {}

    override fun screenRankingStatistics(activity: Activity?) {}

    override fun screenRankingComparator(activity: Activity?) {}

    override fun screenRankingGallery(activity: Activity?) {}

    override fun screenRankingAthlete(activity: Activity?, s: String?) {}

    override fun screenRankingProfile(activity: Activity?) {}

    override fun screenRankingPhoto(activity: Activity?) {}

    override fun currentRaceAndEvent(s: String?, s1: String?) {}

    override fun screenLiveHome(activity: Activity?) {}

    override fun screenLiveSplit(activity: Activity?, s: String?) {}

    override fun screenLiveResults(activity: Activity?) {}

    override fun screenLiveFollows(activity: Activity?) {}

    override fun screenLiveAthlete(activity: Activity?, s: String?) {}

    override fun screenLiveProfile(activity: Activity?) {}

    override fun screenLiveSearch(activity: Activity?) {}

    override fun screenLiveEvent(activity: Activity?) {}

    override fun screenLiveFullScreen(activity: Activity?) {}

    override fun screenLiveFilters(activity: Activity?) {}

    override fun screenLiveSummary(activity: Activity?) {}

    override fun screenLiveTrophies(activity: Activity?) {}

    override fun screenGPS(activity: Activity?) {}

    override fun screenIdentify(activity: Activity?) {}

    override fun eventLiveScreenAbrir() {}

    override fun eventLiveScreenCerrar() {}

    override fun eventInfoCarreraAbrir() {}

    override fun eventInfoCarreraCerrar() {}

    override fun eventScreenHomeRaceSplit() {}

    override fun eventScreenHomeRaceAthlete() {}

    override fun eventScreenHomeRaceZoomIn() {}

    override fun eventScreenHomeRaceZoomOut() {}

    override fun eventScreenSplitPhotos() {}

    override fun eventScreenSplitRanking() {}

    override fun eventScreenSplitClose() {}

    override fun eventScreenSplitAthlete() {}

    override fun eventFollowAthlete(s: String?) {}

    override fun eventFotoOpen() {}

    override fun eventFotoTake() {}

    override fun eventFotoUse() {}

    override fun eventFotoCancel() {}

    override fun eventFotoUploadOK() {}

    override fun eventFotoUploadKO() {}

    override fun eventFotoGpsOK() {}

    override fun eventFotoGpsKO() {}

    override fun eventTwitterOpen() {}

    override fun eventTwitterCancel() {}

    override fun eventTwitterTweet() {}

    override fun eventTwitterFail() {}

    override fun eventBuscarSearch() {}

    override fun eventList() {}

    override fun eventBuscarInfoAthlete() {}

    override fun eventBuscarSuggestedFollow() {}

    override fun eventBuscarSuggestedUnfollow() {}

    override fun eventBuscarAthlete(s: String?) {}

    override fun eventAthleteFollow() {}

    override fun eventAthleteUnfollow() {}

    override fun eventBuscarFollow() {}

    override fun eventBuscarFollowSubmit() {}

    override fun eventBuscarSearchUnfollow() {}

    override fun eventSeguidosInfoAthlete() {}

    override fun eventProfilePhotoTake() {}

    override fun eventProfilePhotoUse() {}

    override fun eventProfilePhotoCancel() {}

    override fun eventProfilePhotoUploadOK() {}

    override fun eventProfilePhotoUploadKO() {}

    override fun eventResultRankingsLeaders() {}

    override fun eventResultRankingsSplitLeaders() {}

    override fun eventResultRankingUpdate() {}

    override fun eventResultRankingsInterval() {}

    override fun eventResultRankingFollow() {}

    override fun eventResultRankingUnfollow() {}

    override fun eventResultRankingLeadersAthlete() {}

    override fun eventResultRankingSplitAthlete() {}

    override fun eventAthleteTiming() {}

    override fun eventAthleteRanking() {}

    override fun eventAthleteTapFollow() {}

    override fun eventAthleteTapUnfollow() {}

    override fun eventAthleteTapPhotos() {}

    override fun eventAthleteTapSplitRanking() {}

    override fun eventAthleteTapVideoRep() {}

    override fun eventAthleteTapVideoMeta() {}

    override fun eventGeneralChangeEvent(s: String?) {}

    override fun userDataNetworkError() {}

    override fun pushNotificationOpenAthlete() {}

    override fun pushNotificationOpenPhotos() {}

    override fun pushNotificationOpenFeaturedPhoto() {}

    override fun pushNotificationOpenCameraOpen() {}

    override fun pushNotificationOpenLive() {}

    override fun pushNotificationOpenTwitter() {}

    override fun pushNotificationOpenPlayVideo() {}

    override fun pushNotificationOpenUrl() {}

    override fun pushNotificationOpenSplit() {}

    override fun sportmaniacsLogged(b: Boolean) {}

    override fun liveIsParticipant(b: Boolean) {}

    override fun liveShareGps(b: Boolean) {}

    override fun sportmaniacsId(s: String?) {}

    override fun eventResultsFilterFullList() {}

    override fun eventResultsFilterLeaders() {}

    override fun eventResultsFilterSplitLeaders() {}

    override fun eventResultsFilterFav() {}

    override fun eventResultsFilterTop10Fav() {}

    override fun eventStartGPS() {}

    override fun eventStopGPS(i: Int, i1: Int) {}

    override fun eventPhotoTake() {}
    override fun timingAborted() {
    }

    override fun eventPhotoUse() {}

    override fun eventPhotoCancel() {}
    override fun endTraining() {
    }

    override fun eventPhotoUploadKO() {}

    override fun eventPhotoUploadOK() {}

    override fun leadModeChosen(s: String?, s1: String?) {}

    override fun leadLoginStarted(s: String?, s1: String?) {}
    override fun feedbackReport(p0: FeedbackReport?) {
    }

    override fun leadLoginEnded(s: String?, s1: String?) {}
    override fun startWarmingUpGPS() {
    }

    override fun startTraining() {
    }

    override fun startTiming() {
    }

    override fun endTiming() {
    }


    override fun eventBrand(s: String?, s1: String?) {}

    override fun eventVideoRepetitionsOpen() {}

    override fun eventAthleteMoveInfo() {}
    override fun errorSynchronization(p0: String?, p1: String?, p2: String?) {
    }
}
*/