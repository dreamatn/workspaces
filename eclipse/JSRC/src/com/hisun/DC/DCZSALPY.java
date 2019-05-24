package com.hisun.DC;

import com.hisun.BP.*;
import com.hisun.SC.*;
import com.hisun.CI.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DCZSALPY {
    DBParm DCTCDDAT_RD;
    DBParm DCTAPPLC_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_OUTPUT_FMT_9 = "DC901";
    int K_MAX_COL = 99;
    int K_MAX_ROW = 50;
    int K_COL_CNT = 8;
    String K_HIS_REMARK = "THE APPLE PAY MANAGEMENT-APPLY";
    String K_HIS_COPYBOOK = "DCRAPPLC";
    String K_TBL_APPLC = "DCTAPPLC";
    String WS_ERR_MSG = " ";
    int WS_CNT = 0;
    char WS_TBL_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    DCRAPPLC DCRAPPLC = new DCRAPPLC();
    DCRCDDAT DCRCDDAT = new DCRCDDAT();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    CICMAGT CICMAGT = new CICMAGT();
    CICCUST CICCUST = new CICCUST();
    CICUAGT CICUAGT = new CICUAGT();
    DCCF901 DCCF901 = new DCCF901();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    DCCS9901 DCCS9901;
    public void MP(SCCGWA SCCGWA, DCCS9901 DCCS9901) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCS9901 = DCCS9901;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCZSALPY return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_APPLY_PROC();
        if (pgmRtn) return;
        B020_HISTORY_PROCESS();
        if (pgmRtn) return;
        B030_DATA_OUTPUT();
        if (pgmRtn) return;
    }
    public void B010_APPLY_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCS9901.MPAN);
        CEP.TRC(SCCGWA, DCCS9901.SPAN);
        IBS.init(SCCGWA, DCRAPPLC);
        T000_READ_DCTAPPLC();
        if (pgmRtn) return;
        if (WS_TBL_FLAG == 'N') {
            DCRAPPLC.MPAN = DCCS9901.MPAN;
            DCRAPPLC.MPAN_ID = DCCS9901.MPAN_ID;
            DCRAPPLC.SE_ID = DCCS9901.SE_ID;
            DCRAPPLC.SE_TYPE = DCCS9901.SE_TYPE;
            DCRAPPLC.SPAN = DCCS9901.SPAN;
            DCRAPPLC.SPAN_ID = DCCS9901.SPAN_ID;
            DCRAPPLC.TEL_NO = DCCS9901.TEL_NO;
            DCRAPPLC.EXP_DT = DCCS9901.EXP_DT;
            DCRAPPLC.STS = "01";
            DCRAPPLC.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
            DCRAPPLC.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DCRAPPLC.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
            DCRAPPLC.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            IBS.init(SCCGWA, CICUAGT);
            CICUAGT.FUNC = 'N';
            CICUAGT.DATA.AGT_TYP = "IBS030";
            CICUAGT.DATA.ENTY_NO = DCCS9901.SPAN;
            CICUAGT.DATA.ENTY_TYP = '2';
            S000_CALL_CIZUAGT();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, CICUAGT.DATA.AGT_NO);
            DCRAPPLC.KEY.AGT_NO = CICUAGT.DATA.AGT_NO;
            T000_WRITE_DCTAPPLC();
            if (pgmRtn) return;
        } else {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_MPAN_CONTRACT_EXIST);
        }
        IBS.init(SCCGWA, DCRCDDAT);
        DCRCDDAT.KEY.CARD_NO = DCCS9901.SPAN;
        T000_READ_DCTCDDAT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DCRCDDAT.CARD_HLDR_CINO);
        IBS.init(SCCGWA, CICCUST);
        CICCUST.FUNC = 'C';
        CICCUST.DATA.CI_NO = DCRCDDAT.CARD_HLDR_CINO;
        S000_CALL_CIZCUST();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICCUST.O_DATA.O_CI_TYP);
        IBS.init(SCCGWA, CICMAGT);
        CICMAGT.FUNC = 'A';
        CICMAGT.DATA.CI_NO = DCRCDDAT.CARD_HLDR_CINO;
        CICMAGT.DATA.CI_TYP = CICCUST.O_DATA.O_CI_TYP;
        CICMAGT.DATA.AGT_NO = CICUAGT.DATA.AGT_NO;
        CICMAGT.DATA.AGT_TYP = "IBS030";
        CICMAGT.DATA.ENTY_TYP = '2';
        CICMAGT.DATA.ENTY_NO = DCCS9901.SPAN;
        CICMAGT.DATA.FRM_APP = "DC";
        CICMAGT.DATA.AGT_LVL = 'A';
        CICMAGT.DATA.EFF_DATE = SCCGWA.COMM_AREA.AC_DATE;
        CICMAGT.DATA.SGN_DATE = SCCGWA.COMM_AREA.AC_DATE;
        CICMAGT.DATA.AGT_STS = 'N';
        S000_CALL_CIZMAGT();
        if (pgmRtn) return;
    }
    public void B020_HISTORY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_TOOL = DCCS9901.SPAN;
        BPCPNHIS.INFO.TX_CD = "0269901";
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARK;
        BPCPNHIS.INFO.FMT_ID = K_HIS_COPYBOOK;
        BPCPNHIS.INFO.FMT_ID_LEN = 375;
        BPCPNHIS.INFO.TX_TYP = 'A';
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        BPCPNHIS.INFO.NEW_DAT_PT = DCRAPPLC;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void B030_DATA_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        IBS.init(SCCGWA, DCCF901);
        DCCF901.MPAN = DCCS9901.MPAN;
        DCCF901.SPAN = DCCS9901.SPAN;
        SCCFMT.FMTID = K_OUTPUT_FMT_9;
        SCCFMT.DATA_PTR = DCCF901;
        SCCFMT.DATA_LEN = 38;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_CIZMAGT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-MAIN-AGT", CICMAGT);
        if (CICMAGT.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICMAGT.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZCUST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-CIZCUST", CICCUST);
        if (CICCUST.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICCUST.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZUAGT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-GNL-AGT-NO", CICUAGT);
        if (CICUAGT.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICUAGT.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS         ", BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void T000_READ_DCTCDDAT() throws IOException,SQLException,Exception {
        DCTCDDAT_RD = new DBParm();
        DCTCDDAT_RD.TableName = "DCTCDDAT";
        IBS.READ(SCCGWA, DCRCDDAT, DCTCDDAT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTCDDAT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_DCTAPPLC() throws IOException,SQLException,Exception {
        DCTAPPLC_RD = new DBParm();
        DCTAPPLC_RD.TableName = "DCTAPPLC";
        DCTAPPLC_RD.where = "MPAN = :DCCS9901.MPAN";
        IBS.READ(SCCGWA, DCRAPPLC, this, DCTAPPLC_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_APPLC;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_UPD_DCTAPPLC() throws IOException,SQLException,Exception {
        DCTAPPLC_RD = new DBParm();
        DCTAPPLC_RD.TableName = "DCTAPPLC";
        DCTAPPLC_RD.where = "MPAN = :DCCS9901.MPAN";
        IBS.READ(SCCGWA, DCRAPPLC, this, DCTAPPLC_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_APPLC;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_WRITE_DCTAPPLC() throws IOException,SQLException,Exception {
        DCTAPPLC_RD = new DBParm();
        DCTAPPLC_RD.TableName = "DCTAPPLC";
        DCTAPPLC_RD.errhdl = true;
        IBS.WRITE(SCCGWA, DCRAPPLC, DCTAPPLC_RD);
        CEP.TRC(SCCGWA, "NIE001");
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            WS_TBL_FLAG = 'D';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_APPLC;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
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
