package com.hisun.DC;

import com.hisun.BP.*;
import com.hisun.SC.*;
import com.hisun.CI.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DCZSAYCG {
    String JIBS_tmp_str[] = new String[10];
    DBParm DCTAPPLC_RD;
    boolean pgmRtn = false;
    String K_OUTPUT_FMT_9 = "DC903";
    int K_MAX_COL = 99;
    int K_MAX_ROW = 50;
    int K_COL_CNT = 8;
    String K_HIS_REMARK = "THE APPLE PAY MANAGEMENT-MODIFY";
    String K_HIS_COPYBOOK = "DCRAPPLC";
    String K_TBL_APPLC = "DCTAPPLC";
    String WS_ERR_MSG = " ";
    String WS_STS = " ";
    String WS_MPAN_ID = " ";
    char WS_TBL_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCTPCL SCCTPCL = new SCCTPCL();
    DCRAPPLC DCRAPPLC = new DCRAPPLC();
    DCRAPPLC DCRAPPLO = new DCRAPPLC();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    CICCUST CICCUST = new CICCUST();
    CICMAGT CICMAGT = new CICMAGT();
    DCCF903 DCCF903 = new DCCF903();
    String WS_SAYCG_MPAN = " ";
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    DCCS9903 DCCS9903;
    public void MP(SCCGWA SCCGWA, DCCS9903 DCCS9903) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCS9903 = DCCS9903;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCZSAYCG return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_MODIFY_PROC();
        if (pgmRtn) return;
        B020_HISTORY_PROCESS();
        if (pgmRtn) return;
        B030_DATA_OUTPUT();
        if (pgmRtn) return;
    }
    public void B010_MODIFY_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCS9903.MPAN);
        CEP.TRC(SCCGWA, DCCS9903.CHANGE_TYPE);
        IBS.init(SCCGWA, DCRAPPLC);
        T000_READ_UPD_DCTAPPLC();
        if (pgmRtn) return;
        WS_MPAN_ID = DCRAPPLC.MPAN_ID;
        CEP.TRC(SCCGWA, WS_MPAN_ID);
        if (WS_TBL_FLAG == 'N') {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_MPAN_CONTRACT_NOT_EX);
        }
        IBS.CLONE(SCCGWA, DCRAPPLC, DCRAPPLO);
        if (DCCS9903.CHANGE_TYPE.equalsIgnoreCase("02")) {
            CEP.TRC(SCCGWA, "N001");
            if (DCRAPPLC.STS.equalsIgnoreCase("03")) {
                CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_MPAN_BEEN_CANCELLED);
            }
            if (DCRAPPLC.STS.equalsIgnoreCase("01")) {
                DCRAPPLC.STS = "02";
                T000_REWRITE_DCTAPPLC();
                if (pgmRtn) return;
            } else {
                CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_MPAN_STS_NOT_NORMAL);
            }
        }
        if (DCCS9903.CHANGE_TYPE.equalsIgnoreCase("01")) {
            CEP.TRC(SCCGWA, "N002");
            if (!DCRAPPLC.STS.equalsIgnoreCase("02")) {
                CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_MPAN_NOT_REPORT_LOSS);
            }
            DCRAPPLC.STS = "01";
            T000_REWRITE_DCTAPPLC();
            if (pgmRtn) return;
        }
        if (DCCS9903.CHANGE_TYPE.equalsIgnoreCase("03")) {
            CEP.TRC(SCCGWA, "N003");
            if (DCRAPPLC.STS.equalsIgnoreCase("03")) {
                CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_MPAN_BEEN_CANCELLED);
            } else {
                DCRAPPLC.STS = "03";
                T000_REWRITE_DCTAPPLC();
                if (pgmRtn) return;
                if (WS_TBL_FLAG == 'D') {
                    WS_ERR_MSG = DCCMSG_ERROR_MSG.DUPKEY;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
            IBS.init(SCCGWA, CICMAGT);
            CICMAGT.FUNC = 'D';
            CICMAGT.DATA.AGT_NO = DCRAPPLC.KEY.AGT_NO;
            CICMAGT.DATA.AGT_TYP = "IBS030";
            CICMAGT.DATA.ENTY_TYP = '2';
            CICMAGT.DATA.ENTY_NO = DCRAPPLC.SPAN;
            S000_CALL_CIZMAGT();
            if (pgmRtn) return;
        }
        if (!DCCS9903.CHANGE_TYPE.equalsIgnoreCase("01") 
            && !DCCS9903.CHANGE_TYPE.equalsIgnoreCase("02") 
            && !DCCS9903.CHANGE_TYPE.equalsIgnoreCase("03")) {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_CHANGE_TYPE_ERROR);
        }
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.REQ_SYSTEM);
    }
    IBS.init(SCCGWA, SCCTPCL);
    SCCTPCL.SERV_AREA.OBJ_SYSTEM = "ESBP";
    SCCTPCL.SERV_AREA.TRANS_FLG = '0';
    SCCTPCL.SERV_AREA.SERV_TYPE = ' ';
    SCCTPCL.INP_AREA.BD_BROADCAST.FUNCTION_ID = "IBS_0203";
    SCCTPCL.INP_AREA.SERV_DATA = IBS.CLS2CPY(SCCGWA, SCCTPCL.INP_AREA.BD_BROADCAST);
    SCCTPCL.INP_AREA.BD_BROADCAST.RECE_SYS_ID = "060800";
    SCCTPCL.INP_AREA.SERV_DATA = IBS.CLS2CPY(SCCGWA, SCCTPCL.INP_AREA.BD_BROADCAST);
    SCCTPCL.INP_AREA.BD_BROADCAST.BD_CARD_LOSS_INF.CARD_TRAN_TYPE = DCCS9903.CHANGE_TYPE;
    JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, SCCTPCL.INP_AREA.BD_BROADCAST.BD_CARD_LOSS_INF);
    IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], SCCTPCL.INP_AREA.BD_BROADCAST.BD_CARD_CHG_INFO);
    SCCTPCL.INP_AREA.BD_BROADCAST.BD_CARD_LOSS_INF.MPAN_ID1 = WS_MPAN_ID;
    JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, SCCTPCL.INP_AREA.BD_BROADCAST.BD_CARD_LOSS_INF);
    IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], SCCTPCL.INP_AREA.BD_BROADCAST.BD_CARD_CHG_INFO);
    SCCTPCL.INP_AREA.SERV_DATA_LEN = 53310;
    IBS.CALLCPN(SCCGWA, "SC-LINK-EXTSERV", SCCTPCL);
    public void B020_HISTORY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_TOOL = DCCS9903.MPAN;
        BPCPNHIS.INFO.TX_CD = "0269903";
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARK;
        BPCPNHIS.INFO.FMT_ID = K_HIS_COPYBOOK;
        BPCPNHIS.INFO.FMT_ID_LEN = 375;
        BPCPNHIS.INFO.TX_TYP = 'M';
        BPCPNHIS.INFO.OLD_DAT_PT = DCRAPPLO;
        BPCPNHIS.INFO.NEW_DAT_PT = DCRAPPLC;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void B030_DATA_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCF903);
        DCCF903.MPAN = DCCS9903.MPAN;
        DCCF903.CHANGE_TYPE = DCCS9903.CHANGE_TYPE;
        SCCFMT.FMTID = K_OUTPUT_FMT_9;
        SCCFMT.DATA_PTR = DCCF903;
        SCCFMT.DATA_LEN = 21;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS", BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
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
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void T000_READ_DCTAPPLC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "N999-READ");
        DCTAPPLC_RD = new DBParm();
        DCTAPPLC_RD.TableName = "DCTAPPLC";
        IBS.READ(SCCGWA, DCRAPPLC, DCTAPPLC_RD);
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
        CEP.TRC(SCCGWA, "N999-READU");
        WS_SAYCG_MPAN = DCCS9903.MPAN;
        DCTAPPLC_RD = new DBParm();
        DCTAPPLC_RD.TableName = "DCTAPPLC";
        DCTAPPLC_RD.where = "MPAN = :WS_SAYCG_MPAN";
        DCTAPPLC_RD.upd = true;
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
    public void T000_REWRITE_DCTAPPLC() throws IOException,SQLException,Exception {
        DCRAPPLC.CHANGE_CHANL_ID = SCCGWA.COMM_AREA.CHNL;
        DCRAPPLC.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        DCRAPPLC.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        CEP.TRC(SCCGWA, "N999-REW");
        DCTAPPLC_RD = new DBParm();
        DCTAPPLC_RD.TableName = "DCTAPPLC";
        DCTAPPLC_RD.errhdl = true;
        IBS.REWRITE(SCCGWA, DCRAPPLC, DCTAPPLC_RD);
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
