package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.CI.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DDZSQCAC {
    DBParm DDTMST_RD;
    brParm DDTCCY_BR = new brParm();
    DBParm DDTVCH_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CPN_UNI_CIZACCU = "CI-INQ-ACCU";
    String WS_ERR_MSG = " ";
    String WS_TS_REC = " ";
    short WS_LEN = 0;
    DDZSQCAC_WS_HEAD_OUT WS_HEAD_OUT = new DDZSQCAC_WS_HEAD_OUT();
    DDZSQCAC_WS_OUTPUT_INF WS_OUTPUT_INF = new DDZSQCAC_WS_OUTPUT_INF();
    char WS_CCY_FLG = ' ';
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    CICACCU CICACCU = new CICACCU();
    CICCUST CICCUST = new CICCUST();
    CICQACRI CICQACRI = new CICQACRI();
    DDRVCH DDRVCH = new DDRVCH();
    DDRMST DDRMST = new DDRMST();
    DDRCCY DDRCCY = new DDRCCY();
    SCCGWA SCCGWA;
    DDCSQCAC DDCSQCAC;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, DDCSQCAC DDCSQCAC) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCSQCAC = DDCSQCAC;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DDZSQCAC return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B030_INQ_AC_INF();
        if (pgmRtn) return;
        B050_CHK_AC_STS();
        if (pgmRtn) return;
        B070_GET_CI_INF_PROC();
        if (pgmRtn) return;
        B090_INQ_CCY_BAL_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCSQCAC.AC_NO);
        CEP.TRC(SCCGWA, DDCSQCAC.AC_CNM);
        CEP.TRC(SCCGWA, DDCSQCAC.AC_ENM);
        CEP.TRC(SCCGWA, DDCSQCAC.DR_CARD);
        CEP.TRC(SCCGWA, DDCSQCAC.PSBK_NO);
        if (DDCSQCAC.AC_NO.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B030_INQ_AC_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACRI);
        CICQACRI.FUNC = 'A';
        CICQACRI.DATA.AGR_NO = DDCSQCAC.AC_NO;
        S000_CALL_CIZQACRI();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_CI_NO);
        if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("DD")) {
            IBS.init(SCCGWA, DDRMST);
            DDRMST.KEY.CUS_AC = CICQACRI.O_DATA.O_AGR_NO;
            T000_READ_DDTMST();
            if (pgmRtn) return;
        }
        if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("DC")) {
            CEP.TRC(SCCGWA, "DC");
        }
    }
    public void B050_CHK_AC_STS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDRMST.AC_STS);
        if (DDRMST.AC_STS == 'C') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_CLOSE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B070_GET_CI_INF_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICCUST);
        CICCUST.FUNC = 'C';
        CICCUST.DATA.CI_NO = CICQACRI.O_DATA.O_CI_NO;
        CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_CI_NO);
        S000_CALL_CIZCUST();
        if (pgmRtn) return;
    }
    public void B090_INQ_CCY_BAL_PROC() throws IOException,SQLException,Exception {
        B050_10_BEGIN_MPAGE_OUTPUT();
        if (pgmRtn) return;
        B050_30_STARTBR_DDTCCY();
        if (pgmRtn) return;
        B050_50_READNEXT_DDTCCY();
        if (pgmRtn) return;
        while (WS_CCY_FLG != 'N' 
            && SCCMPAG.FUNC != 'E') {
            B050_90_OUTPUT_DETAIL();
            if (pgmRtn) return;
            B050_50_READNEXT_DDTCCY();
            if (pgmRtn) return;
        }
        B050_70_ENDBR_DDTCCY();
        if (pgmRtn) return;
    }
    public void B050_10_BEGIN_MPAGE_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 39;
        SCCMPAG.SCR_ROW_CNT = 0;
        SCCMPAG.SCR_COL_CNT = 0;
        B_MPAG();
        if (pgmRtn) return;
        if (DDCSQCAC.PSBK_NO.trim().length() > 0) {
            T000_READ_DDTVCH();
            if (pgmRtn) return;
        }
    }
    public void B050_30_STARTBR_DDTCCY() throws IOException,SQLException,Exception {
        T000_STARTBR_DDTCCY();
        if (pgmRtn) return;
    }
    public void B050_50_READNEXT_DDTCCY() throws IOException,SQLException,Exception {
        T000_READNEXT_DDTCCY();
        if (pgmRtn) return;
    }
    public void B050_70_ENDBR_DDTCCY() throws IOException,SQLException,Exception {
        T000_ENDBR_DDTCCY();
        if (pgmRtn) return;
    }
    public void B050_90_OUTPUT_DETAIL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_OUTPUT_INF);
        WS_OUTPUT_INF.WS_CCY = DDRCCY.CCY;
        WS_OUTPUT_INF.WS_CCY_TYPE = DDRCCY.CCY_TYPE;
        WS_OUTPUT_INF.WS_CURR_BAL = DDRCCY.CURR_BAL;
        WS_OUTPUT_INF.WS_HOLD_BAL = DDRCCY.HOLD_BAL;
        WS_OUTPUT_INF.WS_CCY_STS = DDRCCY.STS;
        CEP.TRC(SCCGWA, WS_OUTPUT_INF.WS_CCY);
        CEP.TRC(SCCGWA, WS_OUTPUT_INF.WS_CCY_TYPE);
        CEP.TRC(SCCGWA, WS_OUTPUT_INF.WS_CURR_BAL);
        CEP.TRC(SCCGWA, WS_OUTPUT_INF.WS_HOLD_BAL);
        CEP.TRC(SCCGWA, WS_OUTPUT_INF.WS_CCY_STS);
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_OUTPUT_INF);
        SCCMPAG.DATA_LEN = 39;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void T000_READ_DDTMST() throws IOException,SQLException,Exception {
        DDTMST_RD = new DBParm();
        DDTMST_RD.TableName = "DDTMST";
        IBS.READ(SCCGWA, DDRMST, DDTMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_MST1_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_DDTCCY() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRCCY);
        DDRCCY.CUS_AC = CICQACRI.O_DATA.O_AGR_NO;
        WS_CCY_FLG = 'N';
        DDTCCY_BR.rp = new DBParm();
        DDTCCY_BR.rp.TableName = "DDTCCY";
        DDTCCY_BR.rp.where = "CUS_AC = :DDRCCY.CUS_AC "
            + "AND AC_TYPE < > '6'";
        DDTCCY_BR.rp.order = "CCY,CCY_TYPE";
        IBS.STARTBR(SCCGWA, DDRCCY, this, DDTCCY_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void T000_READNEXT_DDTCCY() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, DDRCCY, this, DDTCCY_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_CCY_FLG = 'F';
        } else {
            WS_CCY_FLG = 'N';
        }
    }
    public void T000_ENDBR_DDTCCY() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, DDTCCY_BR);
    }
    public void T000_READ_DDTVCH() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCSQCAC.AC_NO);
        IBS.init(SCCGWA, DDRVCH);
        DDRVCH.KEY.CUS_AC = CICQACRI.O_DATA.O_AGR_NO;
        DDRVCH.VCH_TYPE = '1';
        DDTVCH_RD = new DBParm();
        DDTVCH_RD.TableName = "DDTVCH";
        IBS.READ(SCCGWA, DDRVCH, DDTVCH_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_ACPB_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_WRITE_TS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = WS_TS_REC;
        SCCMPAG.DATA_LEN = WS_LEN;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void S000_CALL_CIZACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_UNI_CIZACCU, CICACCU);
    }
    public void S000_CALL_CIZCUST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-CIZCUST", CICCUST);
        if (CICCUST.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICCUST.RC);
        }
    }
    public void S000_CALL_CIZQACRI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACR-INF", CICQACRI);
        if (CICQACRI.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICQACRI.RC);
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
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
