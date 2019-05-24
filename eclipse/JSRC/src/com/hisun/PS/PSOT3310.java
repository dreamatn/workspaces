package com.hisun.PS;

import com.hisun.BP.*;
import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class PSOT3310 {
    DBParm PSTEINF_RD;
    String K_EXG_BK_NO = "001";
    String K_OUTPUT_FMT = "PS331";
    String WS_ERR_MSG = " ";
    String WS_ERR_INFO = " ";
    int WS_ISS_BR_MSG = 0;
    char WS_TABLE_FLG = ' ';
    PSOT3310_WS_FMT WS_FMT = new PSOT3310_WS_FMT();
    short WS_TST_YES = 0;
    short WS_TST_MTH = 0;
    short WS_TST_DAY = 0;
    short WS_TST_NUM = 0;
    PSCMSG_ERROR_MSG PSCMSG_ERROR_MSG = new PSCMSG_ERROR_MSG();
    BPCCCKWD BPCCCKWD = new BPCCCKWD();
    BPCPCMWD BPCPCMWD = new BPCPCMWD();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCLDT SCCCLDT = new SCCCLDT();
    SCCFMT SCCFMT = new SCCFMT();
    PSREINF PSREINF = new PSREINF();
    String WK_RGN_CD = " ";
    String WK_RGN_SEQ = " ";
    String WK_CCY = " ";
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    PSB3310_AWA_3310 PSB3310_AWA_3310;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        CEP.TRC(SCCGWA, PSB3310_AWA_3310.EXG_DT);
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "PSOT3310 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "PSB3310_AWA_3310>");
        PSB3310_AWA_3310 = (PSB3310_AWA_3310) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, PSB3310_AWA_3310.EXG_DT);
        if (PSB3310_AWA_3310.EXG_DT == 0) {
            CEP.TRC(SCCGWA, 1111);
            CEP.TRC(SCCGWA, PSB3310_AWA_3310.EXG_TMS);
            if (PSB3310_AWA_3310.EXG_TMS == 0) {
                CEP.TRC(SCCGWA, 0);
                B100_GET_DATE_TMS();
            }
        }
        B300_OUTPUT_PROC();
    }
    public void B100_GET_DATE_TMS() throws IOException,SQLException,Exception {
        T000_READ_PSTEINF();
        CEP.TRC(SCCGWA, PSREINF.EXG_DT);
        WS_FMT.WS_EXG_TMS = (short) (PSREINF.EXG_TMS + 1);
        if (WS_FMT.WS_EXG_TMS > PSREINF.EXG_JOIN_TMS) {
            IBS.init(SCCGWA, BPCPCMWD);
            BPCPCMWD.CHECK_DATE = PSREINF.EXG_DT;
            BPCPCMWD.DATE_TYPE = 'B';
            BPCPCMWD.CALCD[1-1] = "CN";
            B310_PROC_BPZPCMWD();
            CEP.TRC(SCCGWA, BPCPCMWD.NEXT_WORK_DAY[1-1]);
            WS_FMT.WS_EXG_DT = BPCPCMWD.NEXT_WORK_DAY[1-1];
            if ("1".trim().length() == 0) WS_FMT.WS_EXG_TMS = 0;
            else WS_FMT.WS_EXG_TMS = Short.parseShort("1");
        } else {
            WS_FMT.WS_EXG_DT = PSREINF.EXG_DT;
        }
        CEP.TRC(SCCGWA, WS_FMT.WS_EXG_DT);
        CEP.TRC(SCCGWA, WS_FMT.WS_EXG_TMS);
    }
    public void B300_OUTPUT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = WS_FMT;
        SCCFMT.DATA_LEN = 11;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void T000_READ_PSTEINF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, PSREINF);
        PSREINF.KEY.EXG_BK_NO = SCCGWA.COMM_AREA.TR_BANK;
        PSREINF.KEY.EXG_TX_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        PSTEINF_RD = new DBParm();
        PSTEINF_RD.TableName = "PSTEINF";
        PSTEINF_RD.where = "EXG_TX_BR = :PSREINF.KEY.EXG_TX_BR "
            + "AND EXG_BK_NO = :PSREINF.KEY.EXG_BK_NO";
        PSTEINF_RD.fst = true;
        IBS.READ(SCCGWA, PSREINF, this, PSTEINF_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        CEP.TRC(SCCGWA, PSREINF.EXG_DT);
        CEP.TRC(SCCGWA, PSREINF.EXG_TMS);
        CEP.TRC(SCCGWA, PSREINF.EXG_JOIN_TMS);
    }
    public void B310_PROC_BPZPCMWD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-MUL-WORK-DAY", BPCPCMWD);
        CEP.TRC(SCCGWA, BPCPCMWD.RC.RC_MMO);
        CEP.TRC(SCCGWA, BPCPCMWD.RC.RC_CODE);
        CEP.TRC(SCCGWA, BPCPCMWD.NEXT_WORK_DAY[1-1]);
        CEP.TRC(SCCGWA, BPCPCMWD.NEXT_WORK_DAY[2-1]);
        CEP.TRC(SCCGWA, BPCPCMWD.NEXT_WORK_DAY[3-1]);
        CEP.TRC(SCCGWA, BPCPCMWD.NEXT_WORK_DAY[4-1]);
        CEP.TRC(SCCGWA, BPCPCMWD.NEXT_WORK_DAY[5-1]);
        CEP.TRC(SCCGWA, BPCPCMWD.NEXT_WORK_DAY[6-1]);
        CEP.TRC(SCCGWA, BPCPCMWD.NEXT_WORK_DAY[7-1]);
        CEP.TRC(SCCGWA, BPCPCMWD.HOLIDAY_FLG[1-1]);
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
