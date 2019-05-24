package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT4222 {
    String JIBS_tmp_str[] = new String[10];
    String K_CMP_PARM_CODE_MAINT = "BP-MAINT-PARM-CODE";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCSUBS SCCSUBS = new SCCSUBS();
    BPCSMPCD BPCSMPCD = new BPCSMPCD();
    SCCGWA SCCGWA;
    BPC4226 BPC4226;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT4222 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPC4226 = new BPC4226();
        IBS.init(SCCGWA, BPC4226);
        IBS.CPY2CLS(SCCGWA, GWA_SC_AREA.INP_OUTP_AREA.INP_AREA_PTR, BPC4226);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_MAIN_PROCESS();
    }
    public void B100_MAIN_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSMPCD);
        BPCSMPCD.TYPE = BPC4226.TYPE;
        BPCSMPCD.TYPE_NAME = BPC4226.NAME;
        BPCSMPCD.TYPE_CHG_NAME = BPC4226.CHG_NAME;
        BPCSMPCD.CODE = BPC4226.CODE;
        BPCSMPCD.INFO.CODE_NAME = BPC4226.CD_NAME;
        BPCSMPCD.INFO.CODE_NAME_S = BPC4226.CD_NAME;
        BPCSMPCD.EFF_DATE = BPC4226.EFF_DATE;
        BPCSMPCD.OUTPUT_FLG = 'Y';
        BPCSMPCD.FUNC = 'Q';
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        if (JIBS_tmp_str[0].equalsIgnoreCase("9994222")) {
            BPCSMPCD.FUNC = 'X';
        }
        S000_CALL_BPZSMPCD();
    }
    public void S000_CALL_BPZSMPCD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, K_CMP_PARM_CODE_MAINT, BPCSMPCD);
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
