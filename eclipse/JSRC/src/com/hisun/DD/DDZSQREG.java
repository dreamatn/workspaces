package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.CI.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DDZSQREG {
    DBParm DDTCCY_RD;
    brParm DDTCCY_BR = new brParm();
    brParm DDTDREG_BR = new brParm();
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String PGM_SCSSCLDT = "SCSSCLDT";
    String WS_ERR_MSG = " ";
    DDZSQREG_WS_OUTPUT_INF WS_OUTPUT_INF = new DDZSQREG_WS_OUTPUT_INF();
    int WS_AC_CNT = 0;
    String WS_CI_NO = " ";
    char WS_REC_FLG = ' ';
    char WS_REC1_FLG = ' ';
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCLDT SCCCLDT = new SCCCLDT();
    DDCOQREG DDCOQREG = new DDCOQREG();
    CICACCU CICACCU = new CICACCU();
    CICCUST CICCUST = new CICCUST();
    CICQACRI CICQACRI = new CICQACRI();
    CICQACAC CICQACAC = new CICQACAC();
    DDRDREG DDRDREG = new DDRDREG();
    DDRCCY DDRCCY = new DDRCCY();
    int WS_STR_DATE = 0;
    int WS_END_DATE = 0;
    char WS_DREG_STS = ' ';
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    DDCSQREG DDCSQREG;
    public void MP(SCCGWA SCCGWA, DDCSQREG DDCSQREG) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCSQREG = DDCSQREG;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DDZSQREG return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, DDCOQREG);
        IBS.init(SCCGWA, DDRDREG);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B030_INQ_QREG_LIST_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (DDCSQREG.FUNC == '1') {
            if (DDCSQREG.AC.trim().length() == 0) {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT);
            }
            if (DDCSQREG.END_DATE == 0) {
                DDCSQREG.END_DATE = SCCGWA.COMM_AREA.AC_DATE;
            }
        }
        if (DDCSQREG.FUNC == '2') {
            if (DDCSQREG.END_DATE == 0) {
                DDCSQREG.END_DATE = SCCGWA.COMM_AREA.AC_DATE;
            }
            if (DDCSQREG.STR_DATE == 0) {
                IBS.init(SCCGWA, SCCCLDT);
                SCCCLDT.DATE1 = DDCSQREG.END_DATE;
                SCCCLDT.MTHS = -6;
                S000_CALL_SCSSCLDT();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, SCCCLDT.DATE2);
                DDCSQREG.STR_DATE = SCCCLDT.DATE2;
            }
        }
        WS_STR_DATE = DDCSQREG.STR_DATE;
        WS_END_DATE = DDCSQREG.END_DATE;
        CEP.TRC(SCCGWA, WS_STR_DATE);
        CEP.TRC(SCCGWA, WS_END_DATE);
    }
    public void B030_INQ_QREG_LIST_PROC() throws IOException,SQLException,Exception {
        B030_10_BEGIN_MPAGE_OUTPUT();
        if (pgmRtn) return;
        if (DDCSQREG.FUNC == '1') {
            B031_DD_AC_REC_CHK();
            if (pgmRtn) return;
        } else if (DDCSQREG.FUNC == '2') {
            B032_DD_HISTORY_REC_REC();
            if (pgmRtn) return;
        }
    }
    public void B031_DD_AC_REC_CHK() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACRI);
        CICQACRI.FUNC = 'A';
        CICQACRI.DATA.AGR_NO = DDCSQREG.AC;
        S000_CALL_CIZQACRI();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_AGR_NO);
        CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_AC_CNM);
        WS_OUTPUT_INF.WS_AC_CNM = CICQACRI.O_DATA.O_AC_CNM;
        WS_CI_NO = CICQACRI.O_DATA.O_CI_NO;
        IBS.init(SCCGWA, CICCUST);
        CICCUST.FUNC = 'C';
        CICCUST.DATA.CI_NO = CICQACRI.O_DATA.O_CI_NO;
        S000_CALL_CIZCUST();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDCSQREG.CI_TYP);
        CEP.TRC(SCCGWA, CICCUST.O_DATA.O_CI_TYP);
        if ((DDCSQREG.CI_TYP == '1' 
            && CICCUST.O_DATA.O_CI_TYP != '1') 
            || (DDCSQREG.CI_TYP == '2' 
            && CICCUST.O_DATA.O_CI_TYP == '1')) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_CI_TYP_NOT_MATCH);
        }
        IBS.init(SCCGWA, DDRCCY);
        DDRCCY.CUS_AC = CICQACRI.O_DATA.O_AGR_NO;
        T000_START_READ_DDTCCY_PROC();
        if (pgmRtn) return;
        T000_NEXT_READ_DDTCCY_PROC();
        if (pgmRtn) return;
        while (WS_REC_FLG != 'N' 
            && SCCMPAG.FUNC != 'E') {
            IBS.init(SCCGWA, DDRDREG);
            DDRDREG.KEY.AC = DDRCCY.KEY.AC;
            CEP.TRC(SCCGWA, DDRCCY.KEY.AC);
            T000_START_DDTDREG_1_PROC();
            if (pgmRtn) return;
            T000_NEXT_DDTDREG_1_PROC();
            if (pgmRtn) return;
            while (WS_REC1_FLG != 'N') {
                WS_AC_CNT += 1;
                DDRDREG.CI_NO = WS_CI_NO;
                B032_OUTPUT_DETAIL_PROC();
                if (pgmRtn) return;
                T000_NEXT_DDTDREG_1_PROC();
                if (pgmRtn) return;
            }
            T000_END_DDTDREG_1_PROC();
            if (pgmRtn) return;
            T000_NEXT_READ_DDTCCY_PROC();
            if (pgmRtn) return;
        }
        T000_END_READ_DDTCCY_PROC();
        if (pgmRtn) return;
    }
    public void B032_DD_HISTORY_REC_REC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRDREG);
        DDRDREG.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        T000_START_DDTDREG_1_PROC();
        if (pgmRtn) return;
        T000_NEXT_DDTDREG_1_PROC();
        if (pgmRtn) return;
        while (WS_REC1_FLG != 'N' 
            && SCCMPAG.FUNC != 'E') {
            IBS.init(SCCGWA, CICQACAC);
            CICQACAC.FUNC = 'A';
            CICQACAC.DATA.ACAC_NO = DDRDREG.KEY.AC;
            S000_CALL_CIZQACAC();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACR_DATA.O_CI_NO);
            CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACR_DATA.O_AC_CNM);
            WS_OUTPUT_INF.WS_AC_CNM = CICQACAC.O_DATA.O_ACR_DATA.O_AC_CNM;
            WS_CI_NO = CICQACAC.O_DATA.O_ACR_DATA.O_CI_NO;
            IBS.init(SCCGWA, CICCUST);
            CICCUST.FUNC = 'C';
            CICCUST.DATA.CI_NO = CICQACAC.O_DATA.O_ACR_DATA.O_CI_NO;
            S000_CALL_CIZCUST();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, DDCSQREG.CI_TYP);
            CEP.TRC(SCCGWA, CICCUST.O_DATA.O_CI_TYP);
            if ((DDCSQREG.CI_TYP == '1' 
                && CICCUST.O_DATA.O_CI_TYP != '1') 
                || (DDCSQREG.CI_TYP == '2' 
                && CICCUST.O_DATA.O_CI_TYP == '1')) {
            } else {
                IBS.init(SCCGWA, DDRCCY);
                DDRCCY.KEY.AC = DDRDREG.KEY.AC;
                T000_READ_DDTCCY();
                if (pgmRtn) return;
                WS_AC_CNT += 1;
                DDRDREG.CI_NO = WS_CI_NO;
                B032_OUTPUT_DETAIL_PROC();
                if (pgmRtn) return;
            }
            T000_NEXT_DDTDREG_1_PROC();
            if (pgmRtn) return;
        }
        T000_END_DDTDREG_1_PROC();
        if (pgmRtn) return;
    }
    public void B030_10_BEGIN_MPAGE_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 374;
        SCCMPAG.SCR_ROW_CNT = 0;
        SCCMPAG.SCR_COL_CNT = 0;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B032_OUTPUT_DETAIL_PROC() throws IOException,SQLException,Exception {
        WS_OUTPUT_INF.WS_AC = DDRCCY.CUS_AC;
        WS_OUTPUT_INF.WS_CCY = DDRCCY.CCY;
        WS_OUTPUT_INF.WS_CCY_TYPE = DDRCCY.CCY_TYPE;
        WS_OUTPUT_INF.WS_DREG_DATE = DDRDREG.KEY.APP_DATE;
        if (DDRDREG.STS == '1') {
            WS_OUTPUT_INF.WS_DREG_DATE = DDRDREG.W_DT;
            WS_OUTPUT_INF.WS_CRT_BR = DDRDREG.W_BR;
            WS_OUTPUT_INF.WS_CRT_TLR = DDRDREG.W_TLR;
        }
        if (DDRDREG.STS == '2' 
            || DDRDREG.STS == '9' 
            || DDRDREG.STS == '3' 
            || DDRDREG.STS == '4') {
            WS_OUTPUT_INF.WS_DREG_DATE = DDRDREG.D_DT;
            WS_OUTPUT_INF.WS_CRT_BR = DDRDREG.D_BR;
            WS_OUTPUT_INF.WS_CRT_TLR = DDRDREG.D_TLR;
        }
        if (DDRDREG.STS == '6' 
            || DDRDREG.STS == '0' 
            || DDRDREG.STS == '5' 
            || DDRDREG.STS == '7') {
            WS_OUTPUT_INF.WS_DREG_DATE = DDRDREG.N_DT;
            WS_OUTPUT_INF.WS_CRT_BR = DDRDREG.N_BR;
            WS_OUTPUT_INF.WS_CRT_TLR = DDRDREG.N_TLR;
        }
        if (DDRDREG.STS == '8') {
            WS_OUTPUT_INF.WS_DREG_DATE = DDRDREG.UPD_DT;
        }
        WS_OUTPUT_INF.WS_CI_NO_1 = WS_CI_NO;
        WS_OUTPUT_INF.WS_STS = DDRDREG.STS;
        WS_OUTPUT_INF.WS_BAL = DDRDREG.BAL;
        WS_OUTPUT_INF.WS_INT = DDRDREG.INT;
        WS_OUTPUT_INF.WS_FLG = DDRDREG.FLG;
        WS_OUTPUT_INF.WS_OPN_DT = DDRDREG.OPN_DT;
        WS_OUTPUT_INF.WS_NTF_DT = DDRDREG.NTF_DT;
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_OUTPUT_INF);
        SCCMPAG.DATA_LEN = 374;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B050_FMT_OUTPUT() throws IOException,SQLException,Exception {
    }
    public void S000_CALL_CIZQACRI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACR-INF", CICQACRI);
        if (CICQACRI.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICQACRI.RC);
        }
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC);
        if (CICQACAC.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICQACAC.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZCUST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-CIZCUST", CICCUST);
        if (CICCUST.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICCUST.RC);
        }
    }
    public void T000_READ_DDTCCY() throws IOException,SQLException,Exception {
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        IBS.READ(SCCGWA, DDRCCY, DDTCCY_RD);
    }
    public void T000_START_READ_DDTCCY_PROC() throws IOException,SQLException,Exception {
        DDTCCY_BR.rp = new DBParm();
        DDTCCY_BR.rp.TableName = "DDTCCY";
        DDTCCY_BR.rp.where = "CUS_AC = :DDRCCY.CUS_AC";
        IBS.STARTBR(SCCGWA, DDRCCY, this, DDTCCY_BR);
    }
    public void T000_NEXT_READ_DDTCCY_PROC() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, DDRCCY, this, DDTCCY_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_REC_FLG = 'F';
        } else {
            WS_REC_FLG = 'N';
        }
    }
    public void T000_END_READ_DDTCCY_PROC() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, DDTCCY_BR);
    }
    public void T000_START_DDTDREG_1_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDRDREG.KEY.AC);
        CEP.TRC(SCCGWA, DDCSQREG.STR_DATE);
        CEP.TRC(SCCGWA, DDCSQREG.END_DATE);
        CEP.TRC(SCCGWA, DDRDREG.OWNER_BR);
        CEP.TRC(SCCGWA, DDCSQREG.STS);
        WS_DREG_STS = DDCSQREG.STS;
        if (DDCSQREG.FUNC == '1') {
            DDTDREG_BR.rp = new DBParm();
            DDTDREG_BR.rp.TableName = "DDTDREG";
            DDTDREG_BR.rp.where = "AC = :DDRDREG.KEY.AC "
                + "AND ( :WS_DREG_STS = ' ' "
                + "OR STS = :WS_DREG_STS )";
            IBS.STARTBR(SCCGWA, DDRDREG, this, DDTDREG_BR);
        } else if (DDCSQREG.FUNC == '2') {
            DDTDREG_BR.rp = new DBParm();
            DDTDREG_BR.rp.TableName = "DDTDREG";
            DDTDREG_BR.rp.where = "APP_DATE >= :WS_STR_DATE "
                + "AND APP_DATE <= :WS_END_DATE "
                + "AND ( :WS_DREG_STS = ' ' "
                + "OR STS = :WS_DREG_STS ) "
                + "AND BR = :DDRDREG.BR";
            IBS.STARTBR(SCCGWA, DDRDREG, this, DDTDREG_BR);
        }
    }
    public void T000_NEXT_DDTDREG_1_PROC() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, DDRDREG, this, DDTDREG_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_REC1_FLG = 'F';
        } else {
            WS_REC1_FLG = 'N';
        }
    }
    public void T000_END_DDTDREG_1_PROC() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, DDTDREG_BR);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void S000_CALL_SCSSCLDT() throws IOException,SQLException,Exception {
        SCSSCLDT SCSSCLDT1 = new SCSSCLDT();
        SCSSCLDT1.MP(SCCGWA, SCCCLDT);
        if (SCCCLDT.RC != 0) {
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "LINK SCSSCLDT,CLDT-RC=" + SCCCLDT.RC;
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
        if (SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE == 'E') {
            Z_RET();
            if (pgmRtn) return;
        }
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
