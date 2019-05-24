package com.hisun.LN;

import com.hisun.SC.*;
import com.hisun.BP.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class LNZGLGEE {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_HIS_RMKS = "GUARANTEE CONTRACT";
    String WS_ERR_MSG = " ";
    String WS_OUTPUT_FMT = " ";
    String WS_EVENT_CODE = " ";
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCWOUT SCCWOUT = new SCCWOUT();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCGSCA_SC_AREA GWA_SC_AREA = new SCCGSCA_SC_AREA();
    SCCGBPA_BP_AREA GWA_BP_AREA = new SCCGBPA_BP_AREA();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCLDT SCCCLDT = new SCCCLDT();
    BPCPQPRD BPCPQPRD = new BPCPQPRD();
    BPCPFHIS BPCPFHIS = new BPCPFHIS();
    BPCPEBAS BPCPEBAS = new BPCPEBAS();
    LNCIGVCH LNCIGVCH = new LNCIGVCH();
    LNCIGVCY LNCIGVCY = new LNCIGVCY();
    LNCC31 LNCC31 = new LNCC31();
    SCCGWA SCCGWA;
    LNCGLGEE LNCGLGEE;
    public void MP(SCCGWA SCCGWA, LNCGLGEE LNCGLGEE) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCGLGEE = LNCGLGEE;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZGLGEE return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        LNCGLGEE.RC.RC_MMO = "LN";
        LNCGLGEE.RC.RC_CODE = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNCGLGEE.FUNC);
        B000_CHECK_INPUT();
        if (pgmRtn) return;
        if (LNCGLGEE.FUNC == 'A') {
            B010_PROC_AGRAN();
            if (pgmRtn) return;
        } else if (LNCGLGEE.FUNC == 'C') {
            B020_PROC_CGRAN();
            if (pgmRtn) return;
        } else if (LNCGLGEE.FUNC == 'D') {
            B030_PROC_DGRAN();
            if (pgmRtn) return;
        } else if (LNCGLGEE.FUNC == 'R') {
            B040_PROC_RGRAN();
            if (pgmRtn) return;
        } else if (LNCGLGEE.FUNC == 'P') {
            B050_PROC_RPGRAN();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + LNCGLGEE.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B000_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (LNCGLGEE.DATA.CONT_NO.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_CONT_NO_MUST_INPUT, LNCGLGEE.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, LNCGLGEE.DATA.BOOK_BR);
        if (LNCGLGEE.DATA.BOOK_BR == 0) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_BK_BR_M_INPUT, LNCGLGEE.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (LNCGLGEE.DATA.VAL_DATE == 0) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_VAL_DT_M_INPUT, LNCGLGEE.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (LNCGLGEE.DATA.NEW_AMT == 0) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_MUST_I_AMT, LNCGLGEE.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            if (LNCGLGEE.DATA.NEW_AMT <= 0) {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_MUST_I_AMT, LNCGLGEE.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
        if (LNCGLGEE.DATA.PROD_CD.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.PROD_TYPE_ERR_INPUT, LNCGLGEE.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, LNCGLGEE.DATA.CCY);
        if (LNCGLGEE.DATA.CCY.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_MUST_I_CCY, LNCGLGEE.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B010_PROC_AGRAN() throws IOException,SQLException,Exception {
        WS_EVENT_CODE = "ST";
        B100_GENVCY_PROC();
        if (pgmRtn) return;
    }
    public void B020_PROC_CGRAN() throws IOException,SQLException,Exception {
        WS_EVENT_CODE = "MA";
        B100_GENVCY_PROC();
        if (pgmRtn) return;
    }
    public void B030_PROC_DGRAN() throws IOException,SQLException,Exception {
        WS_EVENT_CODE = "MA";
        B100_GENVCY_PROC();
        if (pgmRtn) return;
    }
    public void B040_PROC_RGRAN() throws IOException,SQLException,Exception {
        WS_EVENT_CODE = "ST";
        B100_GENVCY_PROC();
        if (pgmRtn) return;
    }
    public void B050_PROC_RPGRAN() throws IOException,SQLException,Exception {
        WS_EVENT_CODE = "RP";
        B100_GENVCY_PROC();
        if (pgmRtn) return;
    }
    public void B100_GENVCY_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQPRD);
        IBS.init(SCCGWA, LNCIGVCY);
        BPCPQPRD.PRDT_CODE = LNCGLGEE.DATA.PROD_CD;
        S000_CALL_BPZPQPRD();
        if (pgmRtn) return;
        LNCIGVCY.DATA.CNTR_TYPE = BPCPQPRD.PRDT_MODEL;
        LNCIGVCY.DATA.PROD_CODE_OLD = LNCGLGEE.DATA.PROD_CD;
        LNCIGVCY.DATA.CTA_NO = LNCGLGEE.DATA.CONT_NO;
        if (LNCGLGEE.DATA.SUB_CONT_NO.trim().length() == 0) LNCIGVCY.DATA.SUB_CTA_NO = 0;
        else LNCIGVCY.DATA.SUB_CTA_NO = Integer.parseInt(LNCGLGEE.DATA.SUB_CONT_NO);
        LNCIGVCY.DATA.EVENT_CODE = WS_EVENT_CODE;
        CEP.TRC(SCCGWA, WS_EVENT_CODE);
        CEP.TRC(SCCGWA, LNCIGVCY.DATA.EVENT_CODE);
        LNCIGVCY.DATA.BR_OLD = LNCGLGEE.DATA.BOOK_BR;
        LNCIGVCY.DATA.CCY_INFO[1-1].CCY = LNCGLGEE.DATA.CCY;
        LNCIGVCY.DATA.AMT_INFO[1-1].AMT = LNCGLGEE.DATA.NEW_AMT;
        LNCIGVCY.DATA.BAL_NORMAL = LNCGLGEE.DATA.OLD_AMT;
        LNCIGVCY.DATA.VALUE_DATE = LNCGLGEE.DATA.VAL_DATE;
        LNCIGVCY.DATA.STATUS = LNCGLGEE.DATA.STS_WORD;
        LNCIGVCY.DATA.EFF_DAYS = 0;
        LNCIGVCY.DATA.CI_NO = LNCGLGEE.DATA.CI_NO;
        CEP.TRC(SCCGWA, LNCIGVCY.DATA.CNTR_TYPE);
        CEP.TRC(SCCGWA, LNCIGVCY.DATA.PROD_CODE_OLD);
        CEP.TRC(SCCGWA, LNCIGVCY.DATA.CTA_NO);
        CEP.TRC(SCCGWA, LNCIGVCY.DATA.SUB_CTA_NO);
        CEP.TRC(SCCGWA, LNCIGVCY.DATA.EVENT_CODE);
        CEP.TRC(SCCGWA, LNCIGVCY.DATA.BR_OLD);
        CEP.TRC(SCCGWA, LNCIGVCY.DATA.CCY_INFO[1-1].CCY);
        CEP.TRC(SCCGWA, LNCIGVCY.DATA.AMT_INFO[1-1].AMT);
        CEP.TRC(SCCGWA, LNCIGVCY.DATA.BAL_NORMAL);
        CEP.TRC(SCCGWA, LNCIGVCY.DATA.VALUE_DATE);
        CEP.TRC(SCCGWA, LNCIGVCY.DATA.STATUS);
        CEP.TRC(SCCGWA, LNCIGVCY.DATA.EFF_DAYS);
        CEP.TRC(SCCGWA, LNCIGVCY.DATA.CI_NO);
        S000_CALL_LNZIGVCY();
        if (pgmRtn) return;
    }
    public void B100_GENVCH_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQPRD);
        IBS.init(SCCGWA, LNCIGVCH);
        BPCPQPRD.PRDT_CODE = LNCGLGEE.DATA.PROD_CD;
        S000_CALL_BPZPQPRD();
        if (pgmRtn) return;
        LNCIGVCH.DATA.CNTR_TYPE = BPCPQPRD.PRDT_MODEL;
        LNCIGVCH.DATA.PROD_CODE_OLD = LNCGLGEE.DATA.PROD_CD;
        LNCIGVCH.DATA.CTA_NO = LNCGLGEE.DATA.CONT_NO;
        if (LNCGLGEE.DATA.SUB_CONT_NO.trim().length() == 0) LNCIGVCH.DATA.SUB_CTA_NO = 0;
        else LNCIGVCH.DATA.SUB_CTA_NO = Integer.parseInt(LNCGLGEE.DATA.SUB_CONT_NO);
        LNCIGVCH.DATA.EVENT_CODE = WS_EVENT_CODE;
        CEP.TRC(SCCGWA, WS_EVENT_CODE);
        CEP.TRC(SCCGWA, LNCIGVCH.DATA.EVENT_CODE);
        LNCIGVCH.DATA.BR_OLD = LNCGLGEE.DATA.BOOK_BR;
        LNCIGVCH.DATA.CCY_INFO[1-1].CCY = LNCGLGEE.DATA.CCY;
        LNCIGVCH.DATA.AMT_INFO[1-1].AMT = LNCGLGEE.DATA.NEW_AMT;
        LNCIGVCH.DATA.BAL_NORMAL = LNCGLGEE.DATA.OLD_AMT;
        LNCIGVCH.DATA.VALUE_DATE = LNCGLGEE.DATA.VAL_DATE;
        LNCIGVCH.DATA.STATUS = LNCGLGEE.DATA.STS_WORD;
        LNCIGVCH.DATA.EFF_DAYS = 0;
        LNCIGVCH.DATA.CI_NO = LNCGLGEE.DATA.CI_NO;
        CEP.TRC(SCCGWA, LNCIGVCH.DATA.CNTR_TYPE);
        CEP.TRC(SCCGWA, LNCIGVCH.DATA.PROD_CODE_OLD);
        CEP.TRC(SCCGWA, LNCIGVCH.DATA.CTA_NO);
        CEP.TRC(SCCGWA, LNCIGVCH.DATA.SUB_CTA_NO);
        CEP.TRC(SCCGWA, LNCIGVCH.DATA.EVENT_CODE);
        CEP.TRC(SCCGWA, LNCIGVCH.DATA.BR_OLD);
        CEP.TRC(SCCGWA, LNCIGVCH.DATA.CCY_INFO[1-1].CCY);
        CEP.TRC(SCCGWA, LNCIGVCH.DATA.AMT_INFO[1-1].AMT);
        CEP.TRC(SCCGWA, LNCIGVCH.DATA.BAL_NORMAL);
        CEP.TRC(SCCGWA, LNCIGVCH.DATA.VALUE_DATE);
        CEP.TRC(SCCGWA, LNCIGVCH.DATA.STATUS);
        CEP.TRC(SCCGWA, LNCIGVCH.DATA.EFF_DAYS);
        CEP.TRC(SCCGWA, LNCIGVCH.DATA.CI_NO);
        S000_CALL_LNZIGVCH();
        if (pgmRtn) return;
    }
    public void B200_GENERATE_HIS() throws IOException,SQLException,Exception {
        BPCPFHIS.DATA.JRNNO = LNCGLGEE.DATA.JRN_NO;
        BPCPFHIS.DATA.AC_DT = LNCGLGEE.DATA.VAL_DATE;
        BPCPFHIS.DATA.VCHNO = LNCGLGEE.DATA.VCH_NO;
        BPCPFHIS.DATA.REF_NO = LNCGLGEE.DATA.CONT_NO;
        BPCPFHIS.DATA.AC = LNCGLGEE.DATA.CONT_NO;
        BPCPFHIS.DATA.TX_VAL_DT = LNCGLGEE.DATA.VAL_DATE;
        BPCPFHIS.DATA.TX_CCY = LNCGLGEE.DATA.CCY;
        BPCPFHIS.DATA.TX_AMT = LNCGLGEE.DATA.NEW_AMT;
        BPCPFHIS.DATA.PROD_CD = LNCGLGEE.DATA.PROD_CD;
        BPCPFHIS.DATA.REMARK = K_HIS_RMKS;
        BPCPFHIS.DATA.FMT_CODE = "LNG01";
        BPCPFHIS.DATA.FMT_LEN = 1702;
        S000_CALL_BPZPFHIS();
        if (pgmRtn) return;
    }
    public void R000_INIT_LNCC31() throws IOException,SQLException,Exception {
    }
    public void S000_CALL_LNZIGVCY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-I-GEN-VCY", LNCIGVCY);
        if (LNCIGVCY.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCIGVCY.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCGLGEE.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZIGVCH() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-I-GEN-VCH", LNCIGVCH);
        if (LNCIGVCH.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCIGVCH.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCGLGEE.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPQPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-QUERY-PRDT-INFO", BPCPQPRD);
        if (BPCPQPRD.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQPRD.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCGLGEE.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPFHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PROC-FHIS", BPCPFHIS);
        if (BPCPFHIS.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPFHIS.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCGLGEE.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPEBAS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-WRT-BASIC-EWA", BPCPEBAS);
        if (BPCPEBAS.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPEBAS.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCGLGEE.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void B_MPAG() throws IOException,SQLException,Exception {
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
        JIBS_tmp_str[9] = "SCZMPAG";
        Class<?>clazz = Class.forName(JIBS_tmp_str[9].trim());
        Object obj = clazz.newInstance();
        Method m = clazz.getDeclaredMethod("MP",new Class[]{SCCGWA.getClass(), SCCMPAG.getClass()});
        m.invoke(obj, SCCGWA, SCCMPAG);
        if (SCCGWA.COMM_AREA.EXCP_FLG == 'Y') {
            Z_RET();
            if (pgmRtn) return;
        }
    } else { //FROM #ELSE
    } //FROM #ENDIF
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (LNCGLGEE.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "LNCGLGEE=");
            CEP.TRC(SCCGWA, LNCGLGEE);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
