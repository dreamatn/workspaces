package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT3925 {
    char K_ERROR = 'E';
    String CPN_S_MGM_BACT = "BP-S-MGM-BACT  ";
    String CPN_F_BV_PRM_QUERY = "BP-F-BV-PRM-QUERY   ";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCFBVQU BPCFBVQU = new BPCFBVQU();
    BPCSBACT BPCSBACT = new BPCSBACT();
    SCCGWA SCCGWA;
    BPB3920_AWA_3920 BPB3920_AWA_3920;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT3925 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB3920_AWA_3920>");
        BPB3920_AWA_3920 = (BPB3920_AWA_3920) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPCSBACT);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_BRW_BACT_RECORD();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPB3920_AWA_3920.CODE.trim().length() == 0) {
        } else {
            IBS.init(SCCGWA, BPCFBVQU);
            BPCFBVQU.TX_DATA.KEY.CODE = BPB3920_AWA_3920.CODE;
            S000_CALL_BPZFBVQU();
        }
    }
    public void B020_BRW_BACT_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSBACT);
        BPCSBACT.FUNC = 'B';
        R000_TRANS_DATA_PARAMETER();
        S000_CALL_BPZSBACT();
    }
    public void R000_TRANS_DATA_PARAMETER() throws IOException,SQLException,Exception {
        BPCSBACT.CODE = BPB3920_AWA_3920.CODE;
        BPCSBACT.STAT = BPB3920_AWA_3920.STAT;
        BPCSBACT.BV_KIND = BPB3920_AWA_3920.BV_KIND;
    }
    public void S000_CALL_BPZSBACT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_MGM_BACT, BPCSBACT);
    }
    public void S000_CALL_BPZFBVQU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_BV_PRM_QUERY, BPCFBVQU);
        if (BPCFBVQU.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFBVQU.RC);
            if (BPB3920_AWA_3920.CODE.trim().length() == 0) WS_FLD_NO = 0;
            else WS_FLD_NO = Short.parseShort(BPB3920_AWA_3920.CODE);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
