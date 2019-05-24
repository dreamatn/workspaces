package com.hisun.LN;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.BP.BPCPQORG;
import com.hisun.CI.CICACCU;
import com.hisun.CI.CICCUST;
import com.hisun.CI.CICQACAC;
import com.hisun.CI.CICQACR;
import com.hisun.CI.CICQCIAC;
import com.hisun.CI.CIRACR;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;
import com.hisun.SC.SCCSUBS;

public class LNOT8080 {
    boolean pgmRtn = false;
    String K_FMT_ID = "LN808";
    LNOT8080_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new LNOT8080_WS_TEMP_VARIABLE();
    short WS_FLD_NO = 0;
    String WS_ERR_MSG = " ";
    String WS_CONTRACT_NO = " ";
    String WS_PAY_NO = " ";
    String WS_CI_NO = " ";
    short WS_I = 0;
    double WS_AMT = 0;
    double WS_PAY_AMT = 0;
    double WS_TOT_BAL = 0;
    char WS_O_STS = ' ';
    String WS_STS_WORD = "0000000000000000";
    double WS_DUEINT = 0;
    double WS_DUELC = 0;
    int WS_TOTAL_PAGE = 0;
    int WS_TOTAL_NUM = 0;
    short WS_CURR_PAGE = 0;
    char WS_LAST_PAGE = ' ';
    short WS_PAGE_ROW = 0;
    int WS_NEXT_START_NUM = 0;
    int WS_BAL_CNT = 0;
    short WS_IDX = 0;
    LNOT8080_WS_O_HEAD WS_O_HEAD = new LNOT8080_WS_O_HEAD();
    LNOT8080_WS_O_DATA[] WS_O_DATA = new LNOT8080_WS_O_DATA[10];
    char WS_FOUND_FLG = ' ';
    char WS_CITACR_FOUND_FLG = ' ';
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCSUBS SCCSUBS = new SCCSUBS();
    SCCFMT SCCFMT = new SCCFMT();
    LNRINTA LNRINTA = new LNRINTA();
    LNRPLPI LNRPLPI = new LNRPLPI();
    LNRAGRE LNRAGRE = new LNRAGRE();
    LNRICTL LNRICTL = new LNRICTL();
    LNRAPRD LNRAPRD = new LNRAPRD();
    CICQACR CICQACR = new CICQACR();
    CIRACR CIRACR = new CIRACR();
    LNRCONT LNRCONT = new LNRCONT();
    LNCCNEX LNCCNEX = new LNCCNEX();
    LNRSETL LNRSETL = new LNRSETL();
    BPCPQORG BPCPQORG = new BPCPQORG();
    LNRBALZ LNRBALZ = new LNRBALZ();
    LNRACRU LNRACRU = new LNRACRU();
    CICACCU CICACCU = new CICACCU();
    LNRCTPY LNRCTPY = new LNRCTPY();
    LNCICSTS LNCICSTS = new LNCICSTS();
    LNCICUT LNCICUT = new LNCICUT();
    LNCSSCHE LNCSSCHE = new LNCSSCHE();
    LNCAMDB LNCAMDB = new LNCAMDB();
    LNCSPDQ LNCSPDQ = new LNCSPDQ();
    LNCPCFTA LNCPCFTA = new LNCPCFTA();
    CICQACAC CICQACAC = new CICQACAC();
    CIRACAC CIRACAC = new CIRACAC();
    CIRBAS CIRBAS = new CIRBAS();
    CICQCIAC CICQCIAC = new CICQCIAC();
    CICCUST CICCUST = new CICCUST();
    int WS_COUNT_CNT = 0;
    int WS_START_NUM = 0;
    int TR_ST_START = 0;
    int TR_ST_END = 0;
    int TR_DU_STATR = 0;
    int TR_DU_END = 0;
    double WS_LOW_AMT = 0;
    double WS_HIGH_AMT = 0;
    SCCGWA SCCGWA;
    LNB8080_AWA_8080 LNB8080_AWA_8080;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public LNOT8080() {
        for (int i=0;i<10;i++) WS_O_DATA[i] = new LNOT8080_WS_O_DATA();
    }
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        if (pgmRtn) return;
        B000_MAIN_PROCESS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNOT8080 return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "LNB8080_AWA_8080>");
        LNB8080_AWA_8080 = (LNB8080_AWA_8080) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.SC_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.BP_AREA_PTR;
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        if (pgmRtn) return;
        B200_MAIN_PROCESS();
        if (pgmRtn) return;
    }
