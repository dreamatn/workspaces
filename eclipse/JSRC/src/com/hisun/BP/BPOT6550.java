package com.hisun.BP;

import com.hisun.AI.*;
import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT6550 {
    int JIBS_tmp_int;
    DBParm BPTRHIS_RD;
    String JIBS_tmp_str[] = new String[10];
    BPOT6550_WS_INDEX WS_INDEX = new BPOT6550_WS_INDEX();
    BPOT6550_WS_REC_DATA WS_REC_DATA = new BPOT6550_WS_REC_DATA();
    char WS_STOP_FLAG = ' ';
    AICMSG_ERROR_MSG AICMSG_ERROR_MSG = new AICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCMT BPCMT = new BPCMT();
    BPCIEXR BPCIEXR = new BPCIEXR();
    BPRRHIS BPRRHIS = new BPRRHIS();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCPQBNK_DATA_INFO BPCRBANK;
    BPB6550_AWA_6550 BPB6550_AWA_6550;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT6550 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB6550_AWA_6550>");
        BPB6550_AWA_6550 = (BPB6550_AWA_6550) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPCRBANK = (BPCPQBNK_DATA_INFO) SCCGWA.COMM_AREA.BANK_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        WS_STOP_FLAG = 'N';
        WS_REC_DATA.WS_START = 1;
        WS_REC_DATA.WS_LEN = 66;
        for (WS_INDEX.WS_I = 1; WS_INDEX.WS_I <= 100 
            && WS_STOP_FLAG != 'Y'; WS_INDEX.WS_I += 1) {
            IBS.init(SCCGWA, BPCIEXR);
            if (BPB6550_AWA_6550.EXR_DATA == null) BPB6550_AWA_6550.EXR_DATA = "";
            JIBS_tmp_int = BPB6550_AWA_6550.EXR_DATA.length();
            for (int i=0;i<8192-JIBS_tmp_int;i++) BPB6550_AWA_6550.EXR_DATA += " ";
            IBS.CPY2CLS(SCCGWA, BPB6550_AWA_6550.EXR_DATA.substring(WS_REC_DATA.WS_START - 1, WS_REC_DATA.WS_START + WS_REC_DATA.WS_LEN - 1), BPCIEXR);
            CEP.TRC(SCCGWA, "***** INPUT DATA *****");
            CEP.TRC(SCCGWA, WS_REC_DATA.WS_START);
            CEP.TRC(SCCGWA, BPCIEXR.DATA.CCY);
            CEP.TRC(SCCGWA, BPCIEXR.DATA.TYPE);
            CEP.TRC(SCCGWA, BPCIEXR.DATA.BID);
            CEP.TRC(SCCGWA, BPCIEXR.DATA.ASK);
            CEP.TRC(SCCGWA, BPCIEXR.DATA.MID);
            CEP.TRC(SCCGWA, BPCIEXR.DATA.EFF_DATE);
            if (BPCIEXR.DATA.CCY.trim().length() == 0) {
                WS_STOP_FLAG = 'Y';
            } else {
                B020_ADD_REC_PROC();
                WS_REC_DATA.WS_START = (short) (WS_REC_DATA.WS_START + WS_REC_DATA.WS_LEN);
            }
        }
        if (WS_INDEX.WS_EXR_CNT > 0) {
            B040_WRITE_ANS_HIS();
        }
    }
    public void B020_ADD_REC_PROC() throws IOException,SQLException,Exception {
        if (BPCIEXR.DATA.TYPE == 'D') {
            IBS.init(SCCGWA, BPCMT);
            BPCMT.DATA.FUNC = 'A';
            BPCMT.DATA.EXR_TYPE = BPCRBANK.EX_RA;
            BPCMT.DATA.EFF_DT = BPCIEXR.DATA.EFF_DATE;
            BPCMT.DATA.EFF_TM = SCCGWA.COMM_AREA.TR_TIME;
            BPCMT.DATA.CCY_INFO[1-1].CCY = BPCIEXR.DATA.CCY;
            BPCMT.DATA.CCY_INFO[1-1].FWD_TENOR = " ";
            if (BPCIEXR.DATA.BID.trim().length() == 0) BPCMT.DATA.CCY_INFO[1-1].FX_BUY = 0;
            else BPCMT.DATA.CCY_INFO[1-1].FX_BUY = Double.parseDouble(BPCIEXR.DATA.BID);
            if (BPCIEXR.DATA.ASK.trim().length() == 0) BPCMT.DATA.CCY_INFO[1-1].FX_SELL = 0;
            else BPCMT.DATA.CCY_INFO[1-1].FX_SELL = Double.parseDouble(BPCIEXR.DATA.ASK);
            if (BPCIEXR.DATA.MID.trim().length() == 0) BPCMT.DATA.CCY_INFO[1-1].MID_RAT = 0;
            else BPCMT.DATA.CCY_INFO[1-1].MID_RAT = Double.parseDouble(BPCIEXR.DATA.MID);
            S000_CALL_BPZMT();
            WS_INDEX.WS_EXR_CNT = (short) (WS_INDEX.WS_EXR_CNT + 1);
        }
    }
    public void B040_WRITE_ANS_HIS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRRHIS);
        BPRRHIS.KEY.DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPRRHIS.KEY.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPRRHIS.KEY.TYPE = 'R';
        BPRRHIS.REQ_TYPE = '4';
        BPRRHIS.TIME = SCCGWA.COMM_AREA.TR_TIME;
        T000_INSERT_BPTRHIS();
    }
    public void T000_INSERT_BPTRHIS() throws IOException,SQLException,Exception {
        BPRRHIS.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPTRHIS_RD = new DBParm();
        BPTRHIS_RD.TableName = "BPTRHIS";
        IBS.WRITE(SCCGWA, BPRRHIS, BPTRHIS_RD);
    }
    public void S000_CALL_BPZMT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-MNT-TQP", BPCMT);
        CEP.TRC(SCCGWA, BPCMT.RC);
        if (BPCMT.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCMT.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
