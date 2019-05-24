package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZPQMOV {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CPN_R_ADW_CMOV = "BP-R-ADW-CMOV    ";
    String CPN_R_BRW_MPAR = "BP-R-BRW-MPAR    ";
    String WS_ERR_MSG = " ";
    int WS_I = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    BPRCMOV BPRCMOV = new BPRCMOV();
    BPRMPAR BPRMPAR = new BPRMPAR();
    BPCRMOVF BPCRMOVF = new BPCRMOVF();
    BPCRPARB BPCRPARB = new BPCRPARB();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCPQMOV BPCPQMOV;
    public void MP(SCCGWA SCCGWA, BPCPQMOV BPCPQMOV) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCPQMOV = BPCPQMOV;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZPQMOV return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPRCMOV);
        IBS.init(SCCGWA, BPRMPAR);
        IBS.init(SCCGWA, BPCPQMOV.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHK_INPUT_DATA();
        if (pgmRtn) return;
        B020_QUERY_CMOV();
        if (pgmRtn) return;
    }
    public void B010_CHK_INPUT_DATA() throws IOException,SQLException,Exception {
    }
    public void B020_QUERY_CMOV() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRCMOV);
        IBS.init(SCCGWA, BPCRMOVF);
        CEP.TRC(SCCGWA, BPCPQMOV.DATA_INFO.MOV_DT);
        CEP.TRC(SCCGWA, BPCPQMOV.DATA_INFO.BV_CODE);
        CEP.TRC(SCCGWA, BPCPQMOV.DATA_INFO.CONF_NO);
        CEP.TRC(SCCGWA, BPCPQMOV.DATA_INFO.CCY);
        CEP.TRC(SCCGWA, BPCPQMOV.DATA_INFO.CASH_TYP);
        BPRCMOV.KEY.MOV_DT = BPCPQMOV.DATA_INFO.MOV_DT;
        BPRCMOV.KEY.CONF_NO = BPCPQMOV.DATA_INFO.CONF_NO;
        BPRCMOV.KEY.CASH_TYP = BPCPQMOV.DATA_INFO.CASH_TYP;
        BPRCMOV.KEY.CCY = BPCPQMOV.DATA_INFO.CCY;
        BPCRMOVF.INFO.FUNC = 'Q';
        BPCRMOVF.LEN = 228;
        BPCRMOVF.POINTER = BPRCMOV;
        S000_CALL_BPZTMOVF();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPRCMOV);
        BPCPQMOV.DATA_INFO.MOV_TYP = BPRCMOV.MOV_TYP;
        BPCPQMOV.DATA_INFO.MOV_STS = BPRCMOV.MOV_STS;
        if (BPRCMOV.BR.trim().length() == 0) BPCPQMOV.DATA_INFO.BR = 0;
        else BPCPQMOV.DATA_INFO.BR = Integer.parseInt(BPRCMOV.BR);
        BPCPQMOV.DATA_INFO.CS_KIND = BPRCMOV.CS_KIND;
        BPCPQMOV.DATA_INFO.AMT = BPRCMOV.AMT;
        BPCPQMOV.DATA_INFO.OUT_BR = BPRCMOV.OUT_BR;
        BPCPQMOV.DATA_INFO.OUT_TLR = BPRCMOV.OUT_TLR;
        BPCPQMOV.DATA_INFO.OUT_AC = BPRCMOV.OUT_AC;
        BPCPQMOV.DATA_INFO.IN_BR = BPRCMOV.IN_BR;
        BPCPQMOV.DATA_INFO.IN_TLR = BPRCMOV.IN_TLR;
        BPCPQMOV.DATA_INFO.IN_AC = BPRCMOV.IN_AC;
        BPCPQMOV.DATA_INFO.BV_DT = BPRCMOV.BV_DT;
        BPCPQMOV.DATA_INFO.BV_CODE = BPRCMOV.BV_CODE;
        BPCPQMOV.DATA_INFO.BV_NO = BPRCMOV.BV_NO;
        BPCPQMOV.DATA_INFO.TO_BANK = BPRCMOV.TO_BANK;
        CEP.TRC(SCCGWA, BPCPQMOV.DATA_INFO.TO_BANK);
        CEP.TRC(SCCGWA, BPCPQMOV.DATA_INFO.BV_CODE);
    }
    public void S000_CALL_BPZTMOVF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_ADW_CMOV, BPCRMOVF);
        if (BPCRMOVF.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCRMOVF.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCPQMOV.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZTPARB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_BRW_MPAR, BPCRPARB);
        if (BPCRPARB.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCRPARB.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCPQMOV.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCPQMOV.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCPQMOV = ");
            CEP.TRC(SCCGWA, BPCPQMOV);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
