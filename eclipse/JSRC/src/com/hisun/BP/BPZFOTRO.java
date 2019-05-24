package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZFOTRO {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CPN_R_MGM_GRP = "BP-R-MGM-GRP        ";
    String CPN_R_MGM_GRPL = "BP-R-MGM-GRPL       ";
    char WS_EMP_RECORD = ' ';
    short WS_I = 0;
    char WS_TBL_GRP_FLAG = ' ';
    char WS_TBL_GRPL_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    BPCRGRPL BPCRGRPL = new BPCRGRPL();
    BPRGRPL BPRGRPL = new BPRGRPL();
    BPCRGRP BPCRGRP = new BPCRGRP();
    BPRGRP BPRGRP = new BPRGRP();
    SCCGWA SCCGWA;
    BPCFOTRO BPCFOTRO;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCFOTRO BPCFOTRO) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCFOTRO = BPCFOTRO;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZFOTRO return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPRGRPL);
        IBS.init(SCCGWA, BPCRGRPL);
        IBS.init(SCCGWA, BPRGRP);
        IBS.init(SCCGWA, BPCRGRP);
        IBS.CPY2CLS(SCCGWA, "BP0000", BPCFOTRO.RC);
        BPCFOTRO.PRIV_FLG = 'N';
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_MAIN_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCFOTRO);
        if ((BPCFOTRO.ASSTYP != 'O' 
            && BPCFOTRO.ASSTYP != 'T' 
            && BPCFOTRO.ASSTYP != 'C')) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_FOTRO_ASSTYP, BPCFOTRO.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (BPCFOTRO.ASS_ID.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_FOTRO_ASS_ID, BPCFOTRO.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if ((BPCFOTRO.ATH_TYP != '0' 
            && BPCFOTRO.ATH_TYP != '1')) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_FOTRO_ATHTYP, BPCFOTRO.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (BPCFOTRO.ROLE_CD.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_FOTRO_ROLE_CD, BPCFOTRO.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, BPCRGRP);
            IBS.init(SCCGWA, BPRGRP);
            BPCRGRP.INFO.FUNC = 'Q';
            BPRGRP.KEY.ROLE_CD = BPCFOTRO.ROLE_CD;
            BPCRGRP.INFO.POINTER = BPRGRP;
            S000_CALL_BPZRGRP();
            if (pgmRtn) return;
            if (BPCRGRP.RETURN_INFO == 'N') {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_GRP_NOTFND, BPCFOTRO.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            if (BPRGRP.EFF_DT > SCCGWA.COMM_AREA.AC_DATE 
                || BPRGRP.EXP_DT <= SCCGWA.COMM_AREA.AC_DATE) {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_GRP_INV, BPCFOTRO.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
    }
    public void B020_MAIN_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRGRPL);
        IBS.init(SCCGWA, BPRGRPL);
        BPCRGRPL.INFO.FUNC = 'Q';
        BPRGRPL.KEY.ASS_TYP = BPCFOTRO.ASSTYP;
        BPRGRPL.KEY.ASS_ID = BPCFOTRO.ASS_ID;
        BPRGRPL.KEY.ATH_TYP = BPCFOTRO.ATH_TYP;
        BPCRGRPL.INFO.POINTER = BPRGRPL;
        BPCRGRPL.INFO.LEN = 52;
        S000_CALL_BPZRGRPL();
        if (pgmRtn) return;
        if (BPCRGRPL.RETURN_INFO == 'N') {
            BPCFOTRO.PRIV_FLG = 'N';
            Z_RET();
            if (pgmRtn) return;
        } else {
            WS_TBL_GRPL_FLAG = 'N';
            CEP.TRC(SCCGWA, BPRGRPL.ROLE_CNT);
            for (WS_I = 1; WS_I <= BPRGRPL.ROLE_CNT 
                && WS_TBL_GRPL_FLAG != 'Y'; WS_I += 1) {
                CEP.TRC(SCCGWA, WS_I);
                CEP.TRC(SCCGWA, BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[WS_I-1].ROLE_CD);
                CEP.TRC(SCCGWA, BPCFOTRO.ROLE_CD);
                if (BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[WS_I-1].ROLE_CD.equalsIgnoreCase(BPCFOTRO.ROLE_CD)) {
                    WS_TBL_GRPL_FLAG = 'Y';
                }
            }
            if (WS_TBL_GRPL_FLAG == 'N') {
                CEP.TRC(SCCGWA, "ROLE NO NOTFND");
                BPCFOTRO.PRIV_FLG = 'N';
                Z_RET();
                if (pgmRtn) return;
            }
            CEP.TRC(SCCGWA, "GRPL FOUND");
            CEP.TRC(SCCGWA, WS_I);
            WS_I += -1;
            if (BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[WS_I-1].EFF_DT > SCCGWA.COMM_AREA.AC_DATE 
                || BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[WS_I-1].EXP_DT <= SCCGWA.COMM_AREA.AC_DATE) {
                BPCFOTRO.PRIV_FLG = 'N';
                Z_RET();
                if (pgmRtn) return;
            }
            CEP.TRC(SCCGWA, "AFTER CHECK EFF");
            if ((BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[WS_I-1].MOV_STS == '0' 
                || BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[WS_I-1].MOV_STS == '2' 
                || (BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[WS_I-1].MOV_STS == '1' 
                && (BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[WS_I-1].MOV_EFF_DT < SCCGWA.COMM_AREA.AC_DATE 
                || (BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[WS_I-1].MOV_EFF_DT == SCCGWA.COMM_AREA.AC_DATE 
                && BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[WS_I-1].MOV_EFF_TM < SCCGWA.COMM_AREA.TR_TIME)) 
                && (BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[WS_I-1].MOV_EXP_DT > SCCGWA.COMM_AREA.AC_DATE) 
                || (BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[WS_I-1].MOV_EXP_DT == SCCGWA.COMM_AREA.AC_DATE 
                && BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[WS_I-1].MOV_EXP_TM > SCCGWA.COMM_AREA.TR_TIME)) 
                || (BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[WS_I-1].MOV_STS == '3' 
                && (BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[WS_I-1].MOV_EFF_DT > SCCGWA.COMM_AREA.AC_DATE 
                || (BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[WS_I-1].MOV_EFF_DT == SCCGWA.COMM_AREA.AC_DATE 
                && BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[WS_I-1].MOV_EFF_TM > SCCGWA.COMM_AREA.TR_TIME)) 
                || BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[WS_I-1].MOV_EXP_DT < SCCGWA.COMM_AREA.AC_DATE 
                || (BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[WS_I-1].MOV_EXP_DT == SCCGWA.COMM_AREA.AC_DATE 
                && BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[WS_I-1].MOV_EXP_TM < SCCGWA.COMM_AREA.TR_TIME)))) {
                BPCFOTRO.PRIV_FLG = 'Y';
                Z_RET();
                if (pgmRtn) return;
            }
        }
    }
    public void S000_CALL_BPZRGRP() throws IOException,SQLException,Exception {
        BPCRGRP.INFO.POINTER = BPRGRP;
        BPCRGRP.INFO.LEN = 292;
        IBS.CALLCPN(SCCGWA, "BP-R-MGM-GRP        ", BPCRGRP);
        if (BPCRGRP.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCRGRP.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCFOTRO.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZRGRPL() throws IOException,SQLException,Exception {
        BPCRGRPL.INFO.POINTER = BPRGRPL;
        BPCRGRPL.INFO.LEN = 52;
        IBS.CALLCPN(SCCGWA, "BP-R-MGM-GRPL       ", BPCRGRPL);
        if (BPCRGRPL.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCRGRPL.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCFOTRO.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCFOTRO.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCFOTRO = ");
            CEP.TRC(SCCGWA, BPCFOTRO);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
