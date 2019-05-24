package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT9407 {
    String CPN_S_TATH = "BP-S-MGM-TATH    ";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPRTATH BPRTATH = new BPRTATH();
    BPCSMTAT BPCSMTAT = new BPCSMTAT();
    BPCRTATH BPCRTATH = new BPCRTATH();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPB4850_AWA_4850 BPB4850_AWA_4850;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT9407 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB4850_AWA_4850>");
        BPB4850_AWA_4850 = (BPB4850_AWA_4850) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_DEL_REC_PROC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
    }
    public void B020_DEL_REC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSMTAT);
        IBS.init(SCCGWA, BPCRTATH);
        IBS.init(SCCGWA, BPRTATH);
        BPRTATH.KEY.ASS_TYP = BPB4850_AWA_4850.ASS_TYP;
        BPRTATH.KEY.ASS_ID = BPB4850_AWA_4850.ASS_ID;
        BPRTATH.KEY.ATH_TYP = '0';
        BPCRTATH.INFO.FUNC = 'Q';
        S000_CALL_BPZRTATH();
        CEP.TRC(SCCGWA, BPCRTATH.RETURN_INFO);
        if (BPCRTATH.RETURN_INFO == 'F') {
            if (BPRTATH.TXIF_CNT > 1) {
                BPCSMTAT.FUNC = 'U';
                BPCSMTAT.MODIFY_FUNC = 'D';
            } else {
                BPCSMTAT.FUNC = 'D';
            }
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TATH_NOTFND;
            S000_ERR_MSG_PROC();
        }
        BPCSMTAT.ASS_TYP = BPB4850_AWA_4850.ASS_TYP;
        BPCSMTAT.ASS_ID = BPB4850_AWA_4850.ASS_ID;
        BPCSMTAT.AUTH_TYP = '0';
        BPCSMTAT.IN_FLG = BPB4850_AWA_4850.IN_FLG;
        BPCSMTAT.SYS_MMO = BPB4850_AWA_4850.SYS_MMO;
        BPCSMTAT.TX_CD = BPB4850_AWA_4850.TX_CD;
        BPCSMTAT.TXIF_NUM = BPB4850_AWA_4850.TXIF_NO;
        S00_CALL_BPZSMTAT();
    }
    public void S00_CALL_BPZSMTAT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_TATH, BPCSMTAT);
    }
    public void S000_CALL_BPZRTATH() throws IOException,SQLException,Exception {
        BPCRTATH.INFO.POINTER = BPRTATH;
        BPCRTATH.INFO.LEN = 52;
        IBS.CALLCPN(SCCGWA, "BP-R-MGM-TATH", BPCRTATH);
        if (BPCRTATH.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRTATH.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
