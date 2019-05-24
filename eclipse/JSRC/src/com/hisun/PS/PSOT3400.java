package com.hisun.PS;

import com.hisun.SC.*;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class PSOT3400 {
    int JIBS_tmp_int;
    DBParm PSTEINF_RD;
    String K_OUTPUT_FMT = "PS340";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    PSOT3400_WS_FMT WS_FMT = new PSOT3400_WS_FMT();
    PSOT3400_WS_FMT1 WS_FMT1 = new PSOT3400_WS_FMT1();
    char WS_UPDATE_FLG = ' ';
    char WS_FOUND_FLG = ' ';
    PSREINF PSREINF = new PSREINF();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    PSCMSG_ERROR_MSG PSCMSG_ERROR_MSG = new PSCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    BPCPQORG BPCPQORG = new BPCPQORG();
    int WK_TX_BR = 0;
    String WK_TR_BANK = " ";
    String WK_CCY = " ";
    String WK_AREA_NO = " ";
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        B00_MAIN_PROC();
        CEP.TRC(SCCGWA, "PSOT3400 return!");
        Z_RET();
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B00_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        B02_READ_PSTEINF();
        B22_OUTPUT_PROC();
    }
    public void B22_OUTPUT_PROC() throws IOException,SQLException,Exception {
        WS_FMT1.WS_EXG_DT1 = PSREINF.EXG_DT;
        WS_FMT1.WS_EXG_TMS1 = PSREINF.EXG_TMS;
        WS_FMT1.WS_EXG_NO = PSREINF.EXG_NO;
        CEP.TRC(SCCGWA, PSREINF.EXG_DT);
        CEP.TRC(SCCGWA, PSREINF.EXG_TMS);
        CEP.TRC(SCCGWA, PSREINF.EXG_NO);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = WS_FMT1;
        SCCFMT.DATA_LEN = 23;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B02_READ_PSTEINF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQORG);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
        WK_TX_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        WK_TR_BANK = SCCGWA.COMM_AREA.TR_BANK;
        BPCPQORG.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        S000_CALL_BPZPQORG();
        WK_AREA_NO = "" + BPCPQORG.BRANCH_BR;
        JIBS_tmp_int = WK_AREA_NO.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) WK_AREA_NO = "0" + WK_AREA_NO;
        CEP.TRC(SCCGWA, BPCPQORG.BRANCH_BR);
        CEP.TRC(SCCGWA, WK_AREA_NO);
        PSTEINF_RD = new DBParm();
        PSTEINF_RD.TableName = "PSTEINF";
        PSTEINF_RD.where = "EXG_TX_BR = :WK_TX_BR "
            + "AND EXG_BK_NO = :WK_TR_BANK "
            + "AND EXG_AREA_NO = :WK_AREA_NO";
        PSTEINF_RD.fst = true;
        IBS.READ(SCCGWA, PSREINF, this, PSTEINF_RD);
        WS_FOUND_FLG = ' ';
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FOUND_FLG = 'Y';
        } else {
            WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_ERR_EINF_EXIST;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG", BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            S000_ERR_MSG_PROC();
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
