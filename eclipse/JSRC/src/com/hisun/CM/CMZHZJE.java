package com.hisun.CM;

import com.hisun.CI.*;
import com.hisun.SC.*;
import com.hisun.SC.SCCBINF;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCCLDT;
import com.hisun.SC.SCCCWA;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;
import com.hisun.SC.SCCSUBS;
import com.hisun.DD.*;
import com.hisun.TD.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class CMZHZJE {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "CM100";
    int K_OUTPUT_ROW = 5;
    String K_PRDP_TYP = "PRDPR";
    int K_MAX_ROW = 5;
    int K_MAX_COL = 0;
    int K_COL_CNT = 0;
    String PGM_SCSSCLDT = "SCSSCLDT";
    String WS_ERR_MSG = " ";
    int WS_I = 0;
    int WS_K = 0;
    int WS_T = 0;
    int WS_TT = 0;
    int WS_CNT = 0;
    double WS_SUMBAL1 = 0;
    double WS_SUMBAL2 = 0;
    CMCMSG_ERROR_MSG CMCMSG_ERROR_MSG = new CMCMSG_ERROR_MSG();
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCBINF SCCBINF = new SCCBINF();
    SCCFMT SCCFMT = new SCCFMT();
    SCCSUBS SCCSUBS = new SCCSUBS();
    SCCCLDT SCCCLDT = new SCCCLDT();
    CMCOHZJE CMCOHZJE = new CMCOHZJE();
    DDCIQBAL DDCIQBAL = new DDCIQBAL();
    DDCIQHLD DDCIQHLD = new DDCIQHLD();
    TDCHZJE TDCHZJE = new TDCHZJE();
    SCCGWA SCCGWA;
    SCCCWA SCCCWA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    CMCIHZJE CMCIHZJE;
    public void MP(SCCGWA SCCGWA, CMCIHZJE CMCIHZJE) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CMCIHZJE = CMCIHZJE;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CMZHZJE return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT_PROC();
        if (pgmRtn) return;
        B200_GET_MSG_PROC();
        if (pgmRtn) return;
        B300_OUT_PROC();
        if (pgmRtn) return;
    }
    public void B100_CHECK_INPUT_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CMCIHZJE.AC_NO);
        if (CMCIHZJE.AC_NO.trim().length() == 0) {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_ERR_AC_NO);
        }
    }
    public void B200_GET_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDCHZJE.AC_NO);
        IBS.init(SCCGWA, DDCIQBAL);
        DDCIQBAL.DATA.AC = CMCIHZJE.AC_NO;
        DDCIQBAL.DATA.CCY = "156";
        DDCIQBAL.DATA.CCY_TYPE = '1';
        S000_CALL_DDZIQBAL();
        if (pgmRtn) return;
        CMCOHZJE.BAL3 = DDCIQBAL.DATA.CURR_BAL;
        IBS.init(SCCGWA, DDCIQHLD);
        DDCIQHLD.DATA.CUS_AC = CMCIHZJE.AC_NO;
        DDCIQHLD.DATA.CCY = "156";
        DDCIQHLD.DATA.CCY_TYPE = '1';
        S000_CALL_DDZIQHLD();
        if (pgmRtn) return;
        CMCOHZJE.BAL2 = DDCIQHLD.DATA.HOLD_BAL;
        IBS.init(SCCGWA, TDCHZJE);
        TDCHZJE.AC_NO = CMCIHZJE.AC_NO;
        S000_CALL_TDZHZJE();
        if (pgmRtn) return;
        CMCOHZJE.BAL4 = TDCHZJE.ZCZQ_BAL;
        CMCOHZJE.BAL5 = TDCHZJE.YYSX_BAL;
        CMCOHZJE.BAL6 = TDCHZJE.CZZH_BAL;
        CMCOHZJE.BAL1 = CMCOHZJE.BAL3 + CMCOHZJE.BAL4 + CMCOHZJE.BAL5 + CMCOHZJE.BAL6;
        CMCOHZJE.BAL2 = CMCOHZJE.BAL2 + TDCHZJE.HOLD_BAL;
        CEP.TRC(SCCGWA, CMCOHZJE.BAL1);
        CEP.TRC(SCCGWA, CMCOHZJE.BAL2);
        CEP.TRC(SCCGWA, CMCOHZJE.BAL3);
        CEP.TRC(SCCGWA, CMCOHZJE.BAL4);
        CEP.TRC(SCCGWA, CMCOHZJE.BAL5);
        CEP.TRC(SCCGWA, CMCOHZJE.BAL6);
    }
    public void S000_CALL_TDZHZJE() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-ZXYH-HZJE", TDCHZJE, true);
    }
    public void S000_CALL_DDZIQHLD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-I-INQ-HLD", DDCIQHLD, true);
    }
    public void S000_CALL_DDZIQBAL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-I-INQ-CCY-BAL", DDCIQBAL, true);
    }
    public void B300_OUT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = CMCOHZJE;
        SCCFMT.DATA_LEN = 96;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B_MPAG() throws IOException,SQLException,Exception {
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
        JIBS_tmp_str[9] = "SCZMPAG";
        Class<?>clazz = Class.forName(JIBS_tmp_str[9].trim());
        Object obj = clazz.newInstance();
        Method m = clazz.getDeclaredMethod("MP",new Class[]{SCCGWA.getClass(), SCCMPAG.getClass()});
        m.invoke(obj, SCCGWA, SCCMPAG);
        if (SCCGWA.COMM_AREA.EXCP_FLG == 'Y') {
            Z_RET();
            if (pgmRtn) return;
        }
    } else { //FROM #ELSE
    } //FROM #ENDIF
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
