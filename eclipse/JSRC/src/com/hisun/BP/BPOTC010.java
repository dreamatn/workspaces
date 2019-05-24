package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOTC010 {
    char K_ERROR = 'E';
    String K_OUTPUT_FMT = "BPX01";
    String CPN_F_S_MAIN_FCOM = "BP-BRW-FSAF-HIS";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    short WS_CNT = 0;
    char WS_FUNC_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCOFCOM BPCOFCOM = new BPCOFCOM();
    SCCSUBS SCCSUBS = new SCCSUBS();
    BPCSFSFB BPCSFSFB = new BPCSFSFB();
    BPCCGAC BPCCGAC = new BPCCGAC();
    SCCGWA SCCGWA;
    BPB1055_AWA_1055 BPB1055_AWA_1055;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOTC010 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB1055_AWA_1055>");
        BPB1055_AWA_1055 = (BPB1055_AWA_1055) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPCSFSFB);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        BPCCGAC.DATA.AC_KIND = '2';
        BPCCGAC.DATA.ACO_AC_FLG = '8';
        BPCCGAC.DATA.ACO_AC_FLG = '8';
        BPCCGAC.DATA.ACO_AC_MMO = "11";
        BPCCGAC.DATA.ACO_AC_DEF = 234;
        IBS.CALLCPN(SCCGWA, "BP-GENERTE-ACNO", BPCCGAC);
        CEP.TRC(SCCGWA, BPCCGAC.RC);
        if (BPCCGAC.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCCGAC.RC);
            S000_ERR_MSG_PROC();
            CEP.TRC(SCCGWA, BPCCGAC.DATA.ACO_AC);
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
