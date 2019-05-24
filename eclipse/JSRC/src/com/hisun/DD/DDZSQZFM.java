package com.hisun.DD;

import com.hisun.DC.*;
import com.hisun.SC.*;
import com.hisun.CI.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DDZSQZFM {
    brParm DDTCHQP_BR = new brParm();
    DBParm DDTMST_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_OBJ_SYSTEM = "ZFMM";
    String K_SERV_CODE = "ZFX104";
    String K_OUTPUT_FMT_A = "DD536";
    int WS_SQZFM_STR_DT = 0;
    int WS_SQZFM_STOP_DT = 0;
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    DDZSQZFM_WS_DATA_TO_SCZTPCL WS_DATA_TO_SCZTPCL = new DDZSQZFM_WS_DATA_TO_SCZTPCL();
    DDZSQZFM_WS_OUTPUT_INF WS_OUTPUT_INF = new DDZSQZFM_WS_OUTPUT_INF();
    char WS_CHQP_FLG = ' ';
    char WS_CHQP_BRFLG = ' ';
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCTPCL SCCTPCL = new SCCTPCL();
    DDRCHQP DDRCHQP = new DDRCHQP();
    DDRMST DDRMST = new DDRMST();
    CICQACAC CICQACAC = new CICQACAC();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    DDCSQZFM DDCSQZFM;
    public void MP(SCCGWA SCCGWA, DDCSQZFM DDCSQZFM) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCSQZFM = DDCSQZFM;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DDZSQZFM return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B001_CHECK_INPUT();
        if (pgmRtn) return;
        R000_OPEN_MPAGE_OUTPUT();
        if (pgmRtn) return;
        B010_GET_BRFLG_PROC();
        if (pgmRtn) return;
        B020_PROC_MAIN();
        if (pgmRtn) return;
    }
    public void B001_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCSQZFM.AC);
        IBS.init(SCCGWA, CICQACAC);
        if (DDCSQZFM.AC.trim().length() > 0) {
            CICQACAC.FUNC = 'R';
            CICQACAC.DATA.AGR_NO = DDCSQZFM.AC;
            S000_CALL_CIZQACAC();
            if (pgmRtn) return;
            if (CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR.equalsIgnoreCase("DD")) {
                IBS.init(SCCGWA, DDRMST);
                DDRMST.KEY.CUS_AC = DDCSQZFM.AC;
                T000_READ_DDTMST();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, DDRMST.AC_STS);
            }
        }
    }
    public void B010_GET_BRFLG_PROC() throws IOException,SQLException,Exception {
        if (DDCSQZFM.AC.trim().length() > 0) {
            CEP.TRC(SCCGWA, "222222222222222222");
            WS_CHQP_BRFLG = 'B';
        } else if (DDCSQZFM.AC.trim().length() == 0 
                && DDCSQZFM.STR_DT != 0 
                && DDCSQZFM.STOP_DT != 0) {
            CEP.TRC(SCCGWA, "33333333333333333333333");
            WS_CHQP_BRFLG = 'C';
        } else {
        }
        CEP.TRC(SCCGWA, WS_CHQP_BRFLG);
    }
    public void B020_PROC_MAIN() throws IOException,SQLException,Exception {
        T000_STARTBR_DDTCHQP_PROC();
        if (pgmRtn) return;
        T000_READNEXT_DDTCHQP_PROC();
        if (pgmRtn) return;
        while (WS_CHQP_FLG != 'N' 
            && SCCMPAG.FUNC != 'E') {
            if (WS_CHQP_BRFLG == 'C') {
                IBS.init(SCCGWA, CICQACAC);
                CICQACAC.FUNC = 'A';
                CICQACAC.DATA.ACAC_NO = DDRCHQP.KEY.AC;
                S000_CALL_CIZQACAC();
                if (pgmRtn) return;
                WS_OUTPUT_INF.WS_AC = CICQACAC.O_DATA.O_ACR_DATA.O_AGR_NO;
            } else {
                WS_OUTPUT_INF.WS_AC = DDCSQZFM.AC;
            }
            WS_OUTPUT_INF.WS_AC_NAME = CICQACAC.O_DATA.O_ACR_DATA.O_AC_CNM;
            CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACR_DATA.O_AC_CNM);
            WS_OUTPUT_INF.WS_PASS_NO = DDRCHQP.KEY.PASS_NO;
            WS_OUTPUT_INF.WS_STS = DDRCHQP.KEY.STS;
            WS_OUTPUT_INF.WS_STR_DT = DDRCHQP.KEY.STR_DT;
            WS_OUTPUT_INF.WS_STOP_DT = DDRCHQP.STOP_DT;
            CEP.TRC(SCCGWA, DDRCHQP.KEY.AC);
            CEP.TRC(SCCGWA, DDRCHQP.KEY.PASS_NO);
            CEP.TRC(SCCGWA, DDRCHQP.KEY.STS);
            CEP.TRC(SCCGWA, DDRCHQP.KEY.STR_DT);
            CEP.TRC(SCCGWA, DDRCHQP.STOP_DT);
            R000_OUTPUT_DETAIL();
            if (pgmRtn) return;
            T000_READNEXT_DDTCHQP_PROC();
            if (pgmRtn) return;
        }
        T000_ENDBR_DDTCHQP_PROC();
        if (pgmRtn) return;
    }
    public void R000_OPEN_MPAGE_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 312;
        SCCMPAG.SCR_ROW_CNT = 0;
        SCCMPAG.SCR_COL_CNT = 0;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void R000_OUTPUT_DETAIL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_OUTPUT_INF);
        SCCMPAG.DATA_LEN = 312;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void T000_STARTBR_DDTCHQP_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRCHQP);
        DDRCHQP.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO);
        CEP.TRC(SCCGWA, DDRCHQP.KEY.AC);
        if (WS_CHQP_BRFLG == 'A') {
            WS_SQZFM_STR_DT = DDCSQZFM.STR_DT;
            WS_SQZFM_STOP_DT = DDCSQZFM.STOP_DT;
            DDTCHQP_BR.rp = new DBParm();
            DDTCHQP_BR.rp.TableName = "DDTCHQP";
            DDTCHQP_BR.rp.where = "AC = :CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO "
                + "AND STR_DT >= :WS_SQZFM_STR_DT "
                + "AND STR_DT <= :WS_SQZFM_STOP_DT";
            DDTCHQP_BR.rp.order = "STR_DT DESC";
            IBS.STARTBR(SCCGWA, DDRCHQP, this, DDTCHQP_BR);
        } else if (WS_CHQP_BRFLG == 'B') {
            DDTCHQP_BR.rp = new DBParm();
            DDTCHQP_BR.rp.TableName = "DDTCHQP";
            DDTCHQP_BR.rp.where = "AC = :CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO";
            IBS.STARTBR(SCCGWA, DDRCHQP, this, DDTCHQP_BR);
        } else if (WS_CHQP_BRFLG == 'C') {
            WS_SQZFM_STR_DT = DDCSQZFM.STR_DT;
            WS_SQZFM_STOP_DT = DDCSQZFM.STOP_DT;
            DDTCHQP_BR.rp = new DBParm();
            DDTCHQP_BR.rp.TableName = "DDTCHQP";
            DDTCHQP_BR.rp.where = "STR_DT >= :WS_SQZFM_STR_DT "
                + "AND STR_DT <= :WS_SQZFM_STOP_DT";
            DDTCHQP_BR.rp.order = "STR_DT DESC";
            IBS.STARTBR(SCCGWA, DDRCHQP, this, DDTCHQP_BR);
        } else {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_INPUT_ERR);
        }
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_CHQP_FLG = 'Y';
        } else {
            WS_CHQP_FLG = 'N';
        }
    }
    public void T000_READNEXT_DDTCHQP_PROC() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, DDRCHQP, this, DDTCHQP_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_CHQP_FLG = 'Y';
        } else {
            WS_CHQP_FLG = 'N';
        }
    }
    public void T000_ENDBR_DDTCHQP_PROC() throws IOException,SQLException,Exception {
        DDTCHQP_BR.rp.errhdl = true;
        IBS.ENDBR(SCCGWA, DDTCHQP_BR);
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC);
        CEP.TRC(SCCGWA, CICQACAC.RC);
        if (CICQACAC.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICQACAC.RC);
        }
    }
    public void T000_READ_DDTMST() throws IOException,SQLException,Exception {
        DDTMST_RD = new DBParm();
        DDTMST_RD.TableName = "DDTMST";
        IBS.READ(SCCGWA, DDRMST, DDTMST_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_MST1_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
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
