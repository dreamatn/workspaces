package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT9230 {
    String K_CPN_S_CCY = "BP-INQUIRE-CCY";
    String K_CPN_S_TERM_FTP = "BP-S-TERM-FTP";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCSTFTP BPCSTFTP = new BPCSTFTP();
    BPCQCCY BPCQCCY = new BPCQCCY();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPB9230_AWA_9230 BPB9230_AWA_9230;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT9230 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB9230_AWA_9230>");
        BPB9230_AWA_9230 = (BPB9230_AWA_9230) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        IBS.init(SCCGWA, BPCSTFTP);
        BPCSTFTP.FUNC = 'B';
        BPCSTFTP.INFO.CURRENCY_CODE = BPB9230_AWA_9230.CURRENCY;
        BPCSTFTP.INFO.TENOR_CD = BPB9230_AWA_9230.TENOR_CD;
        S010_CALL_BPZSTFTP();
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPB9230_AWA_9230.CURRENCY.trim().length() > 0) {
            IBS.init(SCCGWA, BPCQCCY);
            BPCQCCY.DATA.CCY = BPB9230_AWA_9230.CURRENCY;
            S010_CALL_BPZSCCYT();
            if (BPCQCCY.RC.RTNCODE != 0) {
                WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCQCCY.RC);
                WS_FLD_NO = BPB9230_AWA_9230.CURRENCY_NO;
                S000_ERR_MSG_PROC();
            }
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S010_CALL_BPZSCCYT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, K_CPN_S_CCY, BPCQCCY);
    }
    public void S010_CALL_BPZSTFTP() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, K_CPN_S_TERM_FTP, BPCSTFTP);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}