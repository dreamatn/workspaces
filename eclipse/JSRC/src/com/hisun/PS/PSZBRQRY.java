package com.hisun.PS;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class PSZBRQRY {
    DBParm PSTEINF_RD;
    char K_ERROR = 'E';
    String K_OUTPUT_FMT = "PS1201";
    String WS_ERR_MSG = " ";
    PSZBRQRY_WS_FMT WS_FMT = new PSZBRQRY_WS_FMT();
    PSCMSG_ERROR_MSG PSCMSG_ERROR_MSG = new PSCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    PSREINF PSREINF = new PSREINF();
    SCCGWA SCCGWA;
    PSCBRQRY PSCBRQRY;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA, PSCBRQRY PSCBRQRY) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.PSCBRQRY = PSCBRQRY;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "PSZBRQRY return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        B200_MAIN_PROC();
        B300_OUTPUT_PROC();
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (PSCBRQRY.TX_BR == 0) {
            WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_TX_BR_MST_INPUT;
            S000_ERR_MSG_PROC();
        }
    }
    public void B200_MAIN_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, PSREINF);
        PSREINF.KEY.EXG_TX_BR = PSCBRQRY.TX_BR;
        T000_READ_EINF();
        if (PSCBRQRY.FLG == '1') {
            if (PSREINF.EXG_SYS_STS.equalsIgnoreCase("01")) {
                WS_FMT.WS_EXG_TMS = PSREINF.EXG_TMS;
                WS_FMT.WS_EXG_DT = PSREINF.EXG_DT;
            } else {
                WS_FMT.WS_EXG_DT = PSREINF.EXG_TMS;
                WS_FMT.WS_EXG_TMS = (short) PSREINF.EXG_DT;
            }
        }
    }
    public void B300_OUTPUT_PROC() throws IOException,SQLException,Exception {
        WS_FMT.WS_EXG_BK_NO = PSREINF.KEY.EXG_BK_NO;
        WS_FMT.WS_EXG_AREA_NO = PSREINF.KEY.EXG_AREA_NO;
        WS_FMT.WS_EXG_CCY = PSREINF.KEY.EXG_CCY;
        WS_FMT.WS_EXG_TX_BR = PSREINF.KEY.EXG_TX_BR;
        WS_FMT.WS_EXG_INSNM = PSREINF.EXG_INSNM;
        WS_FMT.WS_EXG_NO = PSREINF.EXG_NO;
        WS_FMT.WS_EXG_SYS_STS = PSREINF.EXG_SYS_STS;
        WS_FMT.WS_EXG_PRE_DT = PSREINF.EXG_PRE_DT;
        WS_FMT.WS_EXG_PRE_TMS = PSREINF.EXG_PRE_TMS;
        WS_FMT.WS_EXG_BOOK_BR = PSREINF.EXG_BOOK_BR;
        WS_FMT.WS_EXG_CLR_BR = PSREINF.EXG_CLR_BR;
        WS_FMT.WS_EXG_JOIN_TMS = PSREINF.EXG_JOIN_TMS;
        WS_FMT.WS_EXG_TMP_DT = PSREINF.EXG_TMP_DT;
        WS_FMT.WS_TMP_TMS_PERD = PSREINF.TMP_TMS_PERD;
        WS_FMT.WS_TMP_TMS_PERD_UNIT = PSREINF.TMP_TMS_PERD_UNIT;
        WS_FMT.WS_EXG_TMP_TMS = PSREINF.EXG_TMP_TMS;
        WS_FMT.WS_EXG_CLR_AC = PSREINF.EXG_CLR_AC;
        WS_FMT.WS_EXG_YEND_TMS = PSREINF.EXG_YEND_TMS;
        WS_FMT.WS_RMK = PSREINF.RMK;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = WS_FMT;
        SCCFMT.DATA_LEN = 380;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void T000_READ_EINF() throws IOException,SQLException,Exception {
        PSTEINF_RD = new DBParm();
        PSTEINF_RD.TableName = "PSTEINF";
        PSTEINF_RD.fst = true;
        IBS.READ(SCCGWA, PSREINF, PSTEINF_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_BSP_REC_NOTFND;
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
