package com.hisun.TD;

import com.hisun.CM.*;
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

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class TDZFQFX {
    String JIBS_tmp_str[] = new String[10];
    DBParm TDTSMST_RD;
    DBParm TDTCDI_RD;
    DBParm TDTIREV_RD;
    brParm TDTSMST_BR = new brParm();
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "TD800";
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
    int WS_TOT_CNT = 0;
    double WS_TOT_BAL = 0;
    double WS_TOT_LNT = 0;
    int WS_TIME = 0;
    int WS_NUM = 0;
    int WS_TERM = 0;
    int WS_NUM1 = 0;
    char WS_SMST_FLG = ' ';
    char WS_SMST_FLG1 = ' ';
    char WS_IREV_FLG = ' ';
    char WS_CDI_FLG = ' ';
    CMCMSG_ERROR_MSG CMCMSG_ERROR_MSG = new CMCMSG_ERROR_MSG();
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    TDCMSG_ERROR_MSG TDCMSG_ERROR_MSG = new TDCMSG_ERROR_MSG();
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
    TDCOFQFX TDCOFQFX = new TDCOFQFX();
    TDRSMST TDRSMST = new TDRSMST();
    TDRIREV TDRIREV = new TDRIREV();
    TDRCDI TDRCDI = new TDRCDI();
    CICQACAC CICQACAC = new CICQACAC();
    CICQCIAC CICQCIAC = new CICQCIAC();
    SCCGWA SCCGWA;
    SCCCWA SCCCWA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    TDCIFQFX TDCIFQFX;
    public void MP(SCCGWA SCCGWA, TDCIFQFX TDCIFQFX) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.TDCIFQFX = TDCIFQFX;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "TDZFQFX return!");
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
        CEP.TRC(SCCGWA, TDCIFQFX.FUNC);
        CEP.TRC(SCCGWA, TDCIFQFX.CI_NO);
        CEP.TRC(SCCGWA, TDCIFQFX.AC_NO);
        if ((TDCIFQFX.FUNC != '1' 
            && TDCIFQFX.FUNC != '2')) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_FUNC_ERR);
        }
        if (TDCIFQFX.FUNC == '1' 
            && TDCIFQFX.CI_NO.trim().length() == 0) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_CI_NO_MST_IPT);
        }
        if (TDCIFQFX.FUNC == '2' 
            && TDCIFQFX.AC_NO.trim().length() == 0) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_AC_NO_M_INPUT);
        }
    }
    public void B200_GET_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDCIFQFX.AC_NO);
        CEP.TRC(SCCGWA, TDCIFQFX.CI_NO);
        CEP.TRC(SCCGWA, TDCIFQFX.FUNC);
        if (TDCIFQFX.FUNC == '2') {
            IBS.init(SCCGWA, TDRSMST);
            IBS.init(SCCGWA, TDCOFQFX);
            TDRSMST.AC_NO = TDCIFQFX.AC_NO;
            T000_STARTBR_TDTSMST();
            if (pgmRtn) return;
            T000_READNEXT_TDTSMST();
            if (pgmRtn) return;
            while (WS_SMST_FLG != 'N') {
                CEP.TRC(SCCGWA, TDCOFQFX.TOT_CNT);
                TDCOFQFX.TOT_CNT = TDCOFQFX.TOT_CNT + 1;
                CEP.TRC(SCCGWA, TDCOFQFX.TOT_BAL);
                TDCOFQFX.TOT_BAL = TDCOFQFX.TOT_BAL + TDRSMST.BAL;
                CEP.TRC(SCCGWA, TDCOFQFX.TOT_LINT);
                TDCOFQFX.TOT_LINT = TDCOFQFX.TOT_LINT + TDRSMST.DRW_INT;
                WS_NUM += 1;
                TDCOFQFX.AC_DATA[WS_NUM-1].AC_NO = TDRSMST.AC_NO;
                TDCOFQFX.AC_DATA[WS_NUM-1].ACO_AC = TDRSMST.KEY.ACO_AC;
                IBS.init(SCCGWA, CICQACAC);
                CICQACAC.FUNC = 'A';
                CICQACAC.DATA.ACAC_NO = TDRSMST.KEY.ACO_AC;
                S000_CALL_CIZQACAC();
                if (pgmRtn) return;
                TDCOFQFX.AC_DATA[WS_NUM-1].AC_SEQ = CICQACAC.O_DATA.O_ACAC_DATA.O_AGR_SEQ;
                TDCOFQFX.AC_DATA[WS_NUM-1].PROD_CD = TDRSMST.PROD_CD;
                TDCOFQFX.AC_DATA[WS_NUM-1].BAL = TDRSMST.BAL;
                IBS.init(SCCGWA, TDRIREV);
                TDRIREV.KEY.ACO_AC = TDRSMST.KEY.ACO_AC;
                TDRIREV.KEY.IBS_HASH = IBS.CalcHash(99991, TDRIREV.KEY.ACO_AC);
                T000_READ_TDTIREV();
                if (pgmRtn) return;
                TDCOFQFX.AC_DATA[WS_NUM-1].INT_RAT = TDRIREV.CON_RATE;
                TDCOFQFX.AC_DATA[WS_NUM-1].TERM = TDRSMST.TERM;
                TDCOFQFX.AC_DATA[WS_NUM-1].VAL_DATE = TDRSMST.VAL_DATE;
                TDCOFQFX.AC_DATA[WS_NUM-1].EXP_DATE = TDRSMST.EXP_DATE;
                TDCOFQFX.AC_DATA[WS_NUM-1].BAL_INT = TDRSMST.EXP_INT;
                TDCOFQFX.AC_DATA[WS_NUM-1].DRW_INT = TDRSMST.DRW_INT;
                IBS.init(SCCGWA, TDRCDI);
                TDRCDI.KEY.ACO_AC = TDRSMST.KEY.ACO_AC;
                T000_READ_TDTCDI();
                if (pgmRtn) return;
                TDCOFQFX.AC_DATA[WS_NUM-1].MTH_INT = TDRCDI.CD_AMT;
                T000_READNEXT_TDTSMST();
                if (pgmRtn) return;
            }
            T000_ENDBR_TDTSMST();
            if (pgmRtn) return;
        } else {
            CEP.TRC(SCCGWA, TDCOFQFX);
            IBS.init(SCCGWA, CICQCIAC);
            IBS.init(SCCGWA, TDCOFQFX);
            CICQCIAC.FUNC = '2';
            CICQCIAC.DATA.CI_NO = TDCIFQFX.CI_NO;
            S000_CALL_CIZQCIAC();
            if (pgmRtn) return;
            WS_TIME = 0;
            WS_TIME = 1;
            WS_NUM1 = 1;
            CEP.TRC(SCCGWA, "4444444444444");
            CEP.TRC(SCCGWA, CICQCIAC.DATA.ACR_AREA.ENTY_INF[1-1].ENTY_NO);
            while (WS_TIME <= 100 
                && CICQCIAC.DATA.ACR_AREA.ENTY_INF[WS_TIME-1].ENTY_NO.trim().length() != 0) {
                CEP.TRC(SCCGWA, CICQCIAC.DATA.ACR_AREA.ENTY_INF[WS_TIME-1].ENTY_NO);
                CEP.TRC(SCCGWA, WS_TIME);
                IBS.init(SCCGWA, TDRSMST);
                CEP.TRC(SCCGWA, "333333333333");
                TDRSMST.KEY.ACO_AC = CICQCIAC.DATA.ACR_AREA.ENTY_INF[WS_TIME-1].ENTY_NO;
                TDRSMST.KEY.IBS_HASH = IBS.CalcHash(99991, TDRSMST.KEY.ACO_AC);
                CEP.TRC(SCCGWA, TDRSMST.KEY.IBS_HASH);
                T000_READ_TDTSMST();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, "22222222222222222222");
                CEP.TRC(SCCGWA, TDRSMST.KEY.ACO_AC);
                if (WS_SMST_FLG1 == 'Y') {
                    if (TDRSMST.PROD_CD.equalsIgnoreCase("1202011101") 
                        && TDRSMST.ACO_STS == '0') {
                        CEP.TRC(SCCGWA, "55555555555555");
                        CEP.TRC(SCCGWA, TDCOFQFX.TOT_CNT);
                        TDCOFQFX.TOT_CNT = TDCOFQFX.TOT_CNT + 1;
                        CEP.TRC(SCCGWA, TDCOFQFX.TOT_BAL);
                        TDCOFQFX.TOT_BAL = TDCOFQFX.TOT_BAL + TDRSMST.BAL;
                        CEP.TRC(SCCGWA, TDCOFQFX.TOT_LINT);
                        TDCOFQFX.TOT_LINT = TDCOFQFX.TOT_LINT + TDRSMST.DRW_INT;
                        TDCOFQFX.AC_DATA[WS_NUM1-1].AC_NO = TDRSMST.AC_NO;
                        TDCOFQFX.AC_DATA[WS_NUM1-1].ACO_AC = TDRSMST.KEY.ACO_AC;
                        IBS.init(SCCGWA, CICQACAC);
                        CICQACAC.FUNC = 'A';
                        CICQACAC.DATA.ACAC_NO = TDRSMST.KEY.ACO_AC;
                        S000_CALL_CIZQACAC();
                        if (pgmRtn) return;
                        TDCOFQFX.AC_DATA[WS_NUM1-1].AC_SEQ = CICQACAC.O_DATA.O_ACAC_DATA.O_AGR_SEQ;
                        TDCOFQFX.AC_DATA[WS_NUM1-1].PROD_CD = TDRSMST.PROD_CD;
                        TDCOFQFX.AC_DATA[WS_NUM1-1].BAL = TDRSMST.BAL;
                        IBS.init(SCCGWA, TDRIREV);
                        TDRIREV.KEY.ACO_AC = TDRSMST.KEY.ACO_AC;
                        TDRIREV.KEY.IBS_HASH = IBS.CalcHash(99991, TDRIREV.KEY.ACO_AC);
                        T000_READ_TDTIREV();
                        if (pgmRtn) return;
                        TDCOFQFX.AC_DATA[WS_NUM1-1].INT_RAT = TDRIREV.CON_RATE;
                        TDCOFQFX.AC_DATA[WS_NUM1-1].TERM = TDRSMST.TERM;
                        TDCOFQFX.AC_DATA[WS_NUM1-1].VAL_DATE = TDRSMST.VAL_DATE;
                        TDCOFQFX.AC_DATA[WS_NUM1-1].EXP_DATE = TDRSMST.EXP_DATE;
                        TDCOFQFX.AC_DATA[WS_NUM1-1].BAL_INT = TDRSMST.EXP_INT;
                        TDCOFQFX.AC_DATA[WS_NUM1-1].DRW_INT = TDRSMST.DRW_INT;
                        IBS.init(SCCGWA, TDRCDI);
                        TDRCDI.KEY.ACO_AC = TDRSMST.KEY.ACO_AC;
                        T000_READ_TDTCDI();
                        if (pgmRtn) return;
                        TDCOFQFX.AC_DATA[WS_NUM1-1].MTH_INT = TDRCDI.CD_AMT;
                        WS_NUM1 += 1;
                        CEP.TRC(SCCGWA, TDCOFQFX.AC_DATA[WS_NUM1-1]);
                    }
                }
                WS_TIME += 1;
            }
            CEP.TRC(SCCGWA, "1111111111111111111111");
        }
    }
    public void S000_CALL_CIZQCIAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-CUST-ACR", CICQCIAC);
        if (CICQCIAC.RC.RC_CODE != 0 
            && CICQCIAC.RC.RC_CODE != 8051) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICQCIAC.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        } else {
            CICQCIAC.DATA.LAST_FLG = 'Y';
        }
    }
    public void T000_READ_TDTSMST() throws IOException,SQLException,Exception {
        TDTSMST_RD = new DBParm();
        TDTSMST_RD.TableName = "TDTSMST";
        TDTSMST_RD.where = "ACO_AC = :TDRSMST.KEY.ACO_AC";
        IBS.READ(SCCGWA, TDRSMST, this, TDTSMST_RD);
        CEP.TRC(SCCGWA, TDRSMST.KEY.ACO_AC);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_SMST_FLG1 = 'Y';
        } else {
            WS_SMST_FLG1 = 'N';
        }
    }
    public void T000_ENDBR_TDTSMST() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, TDTSMST_BR);
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC);
        if (CICQACAC.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICQACAC.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void T000_READ_TDTCDI() throws IOException,SQLException,Exception {
        TDTCDI_RD = new DBParm();
        TDTCDI_RD.TableName = "TDTCDI";
        TDTCDI_RD.where = "ACO_AC = :TDRCDI.KEY.ACO_AC";
        IBS.READ(SCCGWA, TDRCDI, this, TDTCDI_RD);
        CEP.TRC(SCCGWA, TDRCDI.KEY.ACO_AC);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_CDI_FLG = 'Y';
        } else {
            WS_CDI_FLG = 'N';
        }
        CEP.TRC(SCCGWA, WS_CDI_FLG);
        if (WS_CDI_FLG == 'N') {
            if (TDRSMST.TERM.equalsIgnoreCase("Y003")) {
                WS_TERM = 36;
            }
            if (TDRSMST.TERM.equalsIgnoreCase("Y005")) {
                WS_TERM = 60;
            }
            CEP.TRC(SCCGWA, TDRCDI.CD_AMT);
            CEP.TRC(SCCGWA, TDRSMST.BAL);
            CEP.TRC(SCCGWA, WS_TERM);
            TDRCDI.CD_AMT = TDRSMST.BAL / WS_TERM;
        }
    }
    public void T000_READ_TDTIREV() throws IOException,SQLException,Exception {
        TDTIREV_RD = new DBParm();
        TDTIREV_RD.TableName = "TDTIREV";
        TDTIREV_RD.where = "ACO_AC = :TDRIREV.KEY.ACO_AC "
            + "AND IBS_HASH = :TDRIREV.KEY.IBS_HASH";
        IBS.READ(SCCGWA, TDRIREV, this, TDTIREV_RD);
        CEP.TRC(SCCGWA, TDRIREV.KEY.ACO_AC);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_IREV_FLG = 'Y';
        } else {
            WS_IREV_FLG = 'N';
        }
        if (WS_IREV_FLG == 'N') {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_IREV_NOFND);
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
    public void T000_STARTBR_TDTSMST() throws IOException,SQLException,Exception {
        TDTSMST_BR.rp = new DBParm();
        TDTSMST_BR.rp.TableName = "TDTSMST";
        TDTSMST_BR.rp.where = "AC_NO = :TDRSMST.AC_NO "
            + "AND ACO_STS = '0' "
            + "AND PROD_CD = '1202011101'";
        TDTSMST_BR.rp.order = "VAL_DATE";
        IBS.STARTBR(SCCGWA, TDRSMST, this, TDTSMST_BR);
    }
    public void B300_OUT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = TDCOFQFX;
        SCCFMT.DATA_LEN = 8540;
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
