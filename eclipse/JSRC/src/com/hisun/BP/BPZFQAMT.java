package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZFQAMT {
    String JIBS_tmp_str[] = new String[10];
    String CPN_PARM_MT = "BP-PARM-MAINTAIN    ";
    String WS_ERR_MSG = " ";
    int WS_I = 0;
    int WS_CNT = 0;
    BPZFQAMT_WS_AMTL_KEY WS_AMTL_KEY = new BPZFQAMT_WS_AMTL_KEY();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    BPCPRMM BPCPRMM = new BPCPRMM();
    BPRPAMTL BPRPAMTL = new BPRPAMTL();
    BPRPVMTL BPRPVMTL = new BPRPVMTL();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCFQAMT BPCFQAMT;
    public void MP(SCCGWA SCCGWA, BPCFQAMT BPCFQAMT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCFQAMT = BPCFQAMT;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPZFQAMT return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPCFQAMT.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHK_INPUT_DATA();
        B020_QUERY_VMTL();
        CEP.TRC(SCCGWA, BPCPRMM.RC);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMM.RC);
        if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_PARM_NOTFND)) {
            B030_QUERY_AMTL();
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMM.RC);
            if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_PARM_NOTFND)) {
                WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPRMM.RC);
                S000_ERR_MSG_PROC();
            }
        }
    }
    public void B010_CHK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCFQAMT.DATA_INFO.BR);
        CEP.TRC(SCCGWA, BPCFQAMT.DATA_INFO.MACN_NO);
        if (BPCFQAMT.DATA_INFO.BR == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BR_INPUT_ERROR;
            S000_ERR_MSG_PROC();
        }
        if (BPCFQAMT.DATA_INFO.MACN_NO.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MACHINE_MUST_INPUT;
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_QUERY_VMTL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRPVMTL);
        IBS.init(SCCGWA, BPCPRMM);
        BPRPVMTL.KEY.TYP = "VMTL";
        BPRPVMTL.KEY.CD = SCCGWA.COMM_AREA.REQ_CHNL2;
        BPCPRMM.FUNC = '3';
        BPCPRMM.DAT_PTR = BPRPVMTL;
        S000_CALL_BPZPRMM();
        BPCFQAMT.DATA_INFO.DESC = BPRPVMTL.DESC;
        BPCFQAMT.DATA_INFO.CDSC = BPRPVMTL.CDSC;
        BPCFQAMT.DATA_INFO.TLR_NO = BPRPVMTL.DATA_TXT.TLR_NO;
        BPCFQAMT.DATA_INFO.UPD_DT = BPRPVMTL.DATA_TXT.UPD_DT;
        BPCFQAMT.DATA_INFO.UPD_TLR = BPRPVMTL.DATA_TXT.UPD_TLR;
        CEP.TRC(SCCGWA, BPCFQAMT.DATA_INFO.TLR_NO);
    }
    public void B030_QUERY_AMTL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRPAMTL);
        IBS.init(SCCGWA, BPCPRMM);
        BPRPAMTL.KEY.TYP = "AMTL";
        WS_AMTL_KEY.WS_AMTL_BR = BPCFQAMT.DATA_INFO.BR;
        WS_AMTL_KEY.WS_AMTL_MACN_NO = BPCFQAMT.DATA_INFO.MACN_NO;
        BPRPAMTL.KEY.CD = IBS.CLS2CPY(SCCGWA, WS_AMTL_KEY);
        BPCPRMM.FUNC = '3';
        BPCPRMM.DAT_PTR = BPRPAMTL;
        S000_CALL_BPZPRMM();
        BPCFQAMT.DATA_INFO.DESC = BPRPAMTL.DESC;
        BPCFQAMT.DATA_INFO.CDSC = BPRPAMTL.CDSC;
        BPCFQAMT.DATA_INFO.TLR_NO = BPRPAMTL.DATA_TXT.TLR_NO;
        BPCFQAMT.DATA_INFO.UPD_DT = BPRPAMTL.DATA_TXT.UPD_DT;
        BPCFQAMT.DATA_INFO.UPD_TLR = BPRPAMTL.DATA_TXT.UPD_TLR;
    }
    public void S000_CALL_BPZPRMM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_PARM_MT, BPCPRMM);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCFQAMT.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCFQAMT = ");
            CEP.TRC(SCCGWA, BPCFQAMT);
        }
    } //FROM #ENDIF
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
