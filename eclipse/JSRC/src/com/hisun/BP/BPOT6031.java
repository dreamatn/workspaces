package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT6031 {
    int JIBS_tmp_int;
    String K_S_MAINT_PRDT_PARM = "BP-S-MAINT-PRDT-PARM";
    String K_CMP_SET_SBUS_TRN = "SC-SET-SUBS-TRANS";
    String K_PRDPR_TYPE = "PRDPR";
    String K_OUTPUT_FMT = "BPX01";
    String WS_MSGID = " ";
    BPOT6031_WS_OUTPUT_DATA WS_OUTPUT_DATA = new BPOT6031_WS_OUTPUT_DATA();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCSUBS SCCSUBS = new SCCSUBS();
    BPRPARM BPRPARM = new BPRPARM();
    BPCRMBPM BPCRMBPM = new BPCRMBPM();
    SCCGWA SCCGWA;
    BPC6030 BPC6030;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT6031 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRMBPM);
        IBS.init(SCCGWA, BPRPARM);
        IBS.init(SCCGWA, WS_OUTPUT_DATA);
        BPCRMBPM.RC.RC_MMO = "BP";
        BPCRMBPM.PTR = BPRPARM;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPC6030 = new BPC6030();
        IBS.init(SCCGWA, BPC6030);
        IBS.CPY2CLS(SCCGWA, GWA_SC_AREA.INP_OUTP_AREA.INP_AREA_PTR, BPC6030);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPC6030.PAR_CODP);
        CEP.TRC(SCCGWA, BPC6030.PARM_COD);
        CEP.TRC(SCCGWA, BPC6030.PRD_MODL);
        if (BPC6030.PAR_CODP.trim().length() > 0) {
            B010_CHECK_INPUT();
        }
        B200_MAIN_PROCESS();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPC6030.PAR_CODP);
        CEP.TRC(SCCGWA, BPC6030.PARM_COD);
        CEP.TRC(SCCGWA, BPC6030.PRD_MODL);
        BPCRMBPM.FUNC = 'I';
        BPRPARM.KEY.TYPE = K_PRDPR_TYPE;
        if (BPRPARM.KEY.CODE == null) BPRPARM.KEY.CODE = "";
        JIBS_tmp_int = BPRPARM.KEY.CODE.length();
        for (int i=0;i<40-JIBS_tmp_int;i++) BPRPARM.KEY.CODE += " ";
        BPRPARM.KEY.CODE = "999999" + BPRPARM.KEY.CODE.substring(6);
        if (BPRPARM.KEY.CODE == null) BPRPARM.KEY.CODE = "";
        JIBS_tmp_int = BPRPARM.KEY.CODE.length();
        for (int i=0;i<40-JIBS_tmp_int;i++) BPRPARM.KEY.CODE += " ";
        if (BPC6030.PAR_CODP == null) BPC6030.PAR_CODP = "";
        JIBS_tmp_int = BPC6030.PAR_CODP.length();
        for (int i=0;i<10-JIBS_tmp_int;i++) BPC6030.PAR_CODP += " ";
        BPRPARM.KEY.CODE = BPRPARM.KEY.CODE.substring(0, 7 - 1) + BPC6030.PAR_CODP + BPRPARM.KEY.CODE.substring(7 + BPC6030.PAR_CODP.length() - 1);
        S000_CALL_BPZRMBPM();
        if (BPCRMBPM.RETURN_INFO == 'N') {
            WS_OUTPUT_DATA.WS_PAR_CODP = " ";
            WS_OUTPUT_DATA.WS_PARM_MODEL = " ";
        } else {
            WS_OUTPUT_DATA.WS_PAR_CODP = BPC6030.PAR_CODP;
            if (BPRPARM.BLOB_VAL == null) BPRPARM.BLOB_VAL = "";
            JIBS_tmp_int = BPRPARM.BLOB_VAL.length();
            for (int i=0;i<9500-JIBS_tmp_int;i++) BPRPARM.BLOB_VAL += " ";
            WS_OUTPUT_DATA.WS_PARM_MODEL = BPRPARM.BLOB_VAL.substring(0, 4);
        }
    }
    public void B200_MAIN_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRMBPM);
        IBS.init(SCCGWA, BPRPARM);
        BPCRMBPM.PTR = BPRPARM;
        BPCRMBPM.FUNC = 'I';
        BPRPARM.KEY.TYPE = K_PRDPR_TYPE;
        if (BPRPARM.KEY.CODE == null) BPRPARM.KEY.CODE = "";
        JIBS_tmp_int = BPRPARM.KEY.CODE.length();
        for (int i=0;i<40-JIBS_tmp_int;i++) BPRPARM.KEY.CODE += " ";
        BPRPARM.KEY.CODE = "999999" + BPRPARM.KEY.CODE.substring(6);
        if (BPRPARM.KEY.CODE == null) BPRPARM.KEY.CODE = "";
        JIBS_tmp_int = BPRPARM.KEY.CODE.length();
        for (int i=0;i<40-JIBS_tmp_int;i++) BPRPARM.KEY.CODE += " ";
        if (BPC6030.PARM_COD == null) BPC6030.PARM_COD = "";
        JIBS_tmp_int = BPC6030.PARM_COD.length();
        for (int i=0;i<10-JIBS_tmp_int;i++) BPC6030.PARM_COD += " ";
        BPRPARM.KEY.CODE = BPRPARM.KEY.CODE.substring(0, 7 - 1) + BPC6030.PARM_COD + BPRPARM.KEY.CODE.substring(7 + BPC6030.PARM_COD.length() - 1);
        S000_CALL_BPZRMBPM();
        if (BPCRMBPM.RETURN_INFO == 'N') {
            IBS.init(SCCGWA, BPRPARM);
        }
        B201_FMT_OUTPUT_DATA();
    }
    public void B201_FMT_OUTPUT_DATA() throws IOException,SQLException,Exception {
        WS_OUTPUT_DATA.WS_PARM_CODE = BPRPARM.KEY.CODE.substring(7-1);
        CEP.TRC(SCCGWA, WS_OUTPUT_DATA.WS_PARM_CODE);
        CEP.TRC(SCCGWA, WS_OUTPUT_DATA.WS_PAR_CODP);
        CEP.TRC(SCCGWA, WS_OUTPUT_DATA.WS_PARM_MODEL);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = WS_OUTPUT_DATA;
        SCCFMT.DATA_LEN = 24;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_BPZRMBPM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-R-MBRW-PARM", BPCRMBPM);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_MSGID);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
