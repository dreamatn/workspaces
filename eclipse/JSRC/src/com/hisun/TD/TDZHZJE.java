package com.hisun.TD;

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

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class TDZHZJE {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    brParm TDTSMST_BR = new brParm();
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "TD591";
    int K_OUTPUT_ROW = 5;
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
    double WS_HOLD_BAL = 0;
    char WS_SMST_FLG = ' ';
    TDCMSG_ERROR_MSG TDCMSG_ERROR_MSG = new TDCMSG_ERROR_MSG();
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCBINF SCCBINF = new SCCBINF();
    SCCFMT SCCFMT = new SCCFMT();
    SCCSUBS SCCSUBS = new SCCSUBS();
    SCCCLDT SCCCLDT = new SCCCLDT();
    CICQACRL CICQACRL = new CICQACRL();
    TDRSMST TDRSMST = new TDRSMST();
    SCCGWA SCCGWA;
    SCCCWA SCCCWA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    TDCHZJE TDCHZJE;
    public void MP(SCCGWA SCCGWA, TDCHZJE TDCHZJE) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.TDCHZJE = TDCHZJE;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "TDZHZJE return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        IBS.init(SCCGWA, TDRSMST);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT_PROC();
        if (pgmRtn) return;
        B200_GET_MSG_PROC();
        if (pgmRtn) return;
    }
    public void B100_CHECK_INPUT_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDCHZJE.AC_NO);
        if (TDCHZJE.AC_NO.trim().length() == 0) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_AC_MUST_INPUT);
        }
    }
    public void B200_GET_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDCHZJE.AC_NO);
        TDRSMST.AC_NO = TDCHZJE.AC_NO;
        T000_START_TDTSMST();
        if (pgmRtn) return;
        T000_READNEXT_TDTSMST();
        if (pgmRtn) return;
        while (WS_SMST_FLG != 'N') {
            CEP.TRC(SCCGWA, TDRSMST.KEY.ACO_AC);
            CEP.TRC(SCCGWA, TDRSMST.BAL);
            CEP.TRC(SCCGWA, TDRSMST.PRDAC_CD);
            CEP.TRC(SCCGWA, TDRSMST.PROD_CD);
            if (TDRSMST.PRDAC_CD.equalsIgnoreCase("020") 
                && TDRSMST.PROD_CD.equalsIgnoreCase("5201010301")) {
                WS_SUMBAL1 = WS_SUMBAL1 + TDRSMST.BAL;
            }
            if (TDRSMST.PRDAC_CD.equalsIgnoreCase("027") 
                && TDRSMST.PROD_CD.equalsIgnoreCase("5201010101")) {
                WS_SUMBAL2 = WS_SUMBAL2 + TDRSMST.BAL;
            }
            if (!TDRSMST.PRDAC_CD.equalsIgnoreCase("035")) {
                if (TDRSMST.STSW == null) TDRSMST.STSW = "";
                JIBS_tmp_int = TDRSMST.STSW.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
                if (TDRSMST.STSW == null) TDRSMST.STSW = "";
                JIBS_tmp_int = TDRSMST.STSW.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
                if (TDRSMST.STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1") 
                    || TDRSMST.STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) {
                    if (TDRSMST.BAL >= TDRSMST.HBAL) {
                        WS_HOLD_BAL = TDRSMST.BAL + WS_HOLD_BAL;
                    }
                    if (TDRSMST.BAL < TDRSMST.HBAL) {
                        WS_HOLD_BAL = TDRSMST.HBAL + WS_HOLD_BAL;
                    }
                } else {
                    WS_HOLD_BAL = TDRSMST.HBAL + WS_HOLD_BAL;
                }
            }
            T000_READNEXT_TDTSMST();
            if (pgmRtn) return;
        }
        TDCHZJE.HOLD_BAL = WS_HOLD_BAL;
        TDCHZJE.ZCZQ_BAL = WS_SUMBAL1;
        TDCHZJE.YYSX_BAL = WS_SUMBAL2;
    }
    public void S000_CALL_CIZQACRL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACRL", CICQACRL);
        if (CICQACRL.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICQACRL.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void T000_READNEXT_TDTSMST() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, TDRSMST, this, TDTSMST_BR);
        CEP.TRC(SCCGWA, TDRSMST.KEY.ACO_AC);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_SMST_FLG = 'Y';
        } else {
            WS_SMST_FLG = 'N';
        }
    }
    public void T000_START_TDTSMST() throws IOException,SQLException,Exception {
        TDTSMST_BR.rp = new DBParm();
        TDTSMST_BR.rp.TableName = "TDTSMST";
        TDTSMST_BR.rp.where = "( AC_NO = :TDRSMST.AC_NO "
            + "AND ACO_STS = '0' )";
        TDTSMST_BR.rp.order = "PRDAC_CD";
        IBS.STARTBR(SCCGWA, TDRSMST, this, TDTSMST_BR);
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
